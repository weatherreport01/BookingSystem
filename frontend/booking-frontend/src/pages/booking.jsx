import { useState } from 'react';

function Booking (){
    const [rooms, setRooms] = useState([]);
    const [checkInDate,setCheckInDate] = useState('');
    const [checkOutDate,setCheckOutDate] = useState('');
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [feedback, setFeedback] = useState('');

    const handleSearch = () => {
        setFeedback('');
        if (!checkInDate || !checkOutDate){
            setFeedback('Please select both check-in and check-out dates');
            return;
        }
        if (new Date(checkInDate) >= new Date(checkOutDate)){
            setFeedback('Please select a valid check-in and check-out date!');
            return;
        }
        fetch(`http://localhost:8080/api/booking/availableRooms?checkInDate=${checkInDate}&checkOutDate=${checkOutDate}`)
            .then(response => response.json())
            .then(data => {
                setRooms(data);
                setFeedback("Rooms Found!");
                setTimeout(()=> setFeedback(''),3000);
            })
            .catch(error=>{
                console.error('Search failed:', error);
                setFeedback('Failed to Find rooms. Try again later.');
            });

    };

    const handleBooking = () => {
        const bookingData = {
            roomId: selectedRoom.roomId, 
            checkInDate: checkInDate,
            checkOutDate: checkOutDate
        };
        fetch(`http://localhost:8080/api/booking/book`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'}, // need to add jwt stuff to backend
            body: JSON.stringify(bookingData)
        })
        .then(response => response.json())
        .then(data => {
            setSelectedRoom(null);
            setFeedback("Booking confirmed!");
            setTimeout(()=> setFeedback(''),3000);
        })
        .catch(error => {
            console.error('Booking failed:', error);
            setFeedback('Booking Failed. Try again later.');
        });
    };

    return(
        <>
            <h1>This is the booking page!</h1>
            {!selectedRoom && (
            <div>
                <input type="datetime-local" onChange={(e)=>setCheckInDate(e.target.value)}/>
                <input type="datetime-local" onChange={(e)=>setCheckOutDate(e.target.value)}/>
                <button onClick={handleSearch} disabled={!checkInDate || !checkOutDate}>Search</button>

                <ul>
                    {rooms.map((room)=>(
                        <li key={room.roomId}>
                            Room: {room.roomNumber} | Type: {room.roomType}
                            <button onClick={()=> {setSelectedRoom(room)}}>Select</button>
                        </li>
                    ))}
                </ul>
            </div> 
            )} 

            {rooms.length === 0 && checkInDate && checkOutDate && <p>No available rooms</p>}          
            
            {selectedRoom && (
                <div> 
                    <h2>Book {selectedRoom.roomNumber}</h2>
                    <p>Check in: {checkInDate}</p>
                    <p>Check out: {checkOutDate}</p>
                    <p>Type: {selectedRoom.roomType}</p>
                    <button onClick={handleBooking}>Confirm Booking</button>
                    <button onClick={()=> setSelectedRoom(null)}>Cancel</button>
                </div>
            )}
                {feedback && <p>{feedback}</p>}
            
        </>
    );
}

export default Booking