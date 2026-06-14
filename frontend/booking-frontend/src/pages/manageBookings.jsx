import { useState, useEffect } from "react";


// user must be signed in to access this page
function ManageBookings(){
    const [feedback, setFeedback] = useState('');
    const [bookings,setBookings] = useState([]);
    const [selectedBooking, setSelectedBooking] = useState();
    const [newCheckIn,setNewCheckIn] = useState('');
    const [newCheckOut,setNewCheckOut] = useState('');
    const [manageAction,setManageAction] = useState();

    // when this page mounts, this function will run
    useEffect(()=>{ handleDisplayBookings()},[]);

    // whenever selectedBooking gets updates, it will update check in and check out values
    useEffect(()=> { 
        if (selectedBooking){
            setNewCheckIn(selectedBooking.checkInDate);
            setNewCheckOut(selectedBooking.checkOutDate);
        }
    }, [selectedBooking]);

    const handleDisplayBookings = async () => {
        try{
            const response = await fetch(`http://localhost:8080/api/v1/booking/currentBookings`,{
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('authToken')}`}
                }
            );

            if(response.ok){
                const data = await response.json();
                setBookings(data);
            }

        }catch(error) {
            setFeedback("Something went wrong. Try again later.")
        };
    };

    const handleCancelBooking = async (bookingId) => {
        setFeedback('');
        try{
            const response = await fetch(`http://localhost:8080/api/v1/booking/cancel`,{
                method: 'DELETE',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('authToken')}`},
                body: JSON.stringify({id:bookingId})
                }
            );

            if(response.ok){
                setFeedback("Booking cancelled!");
                handleDisplayBookings();
            }
            else{
                setFeedback("Cancellation failed. Try again.");
            }
        }catch(error){
            setFeedback("Something went wrong. Try again later.");
        };
    };
    
    const handleManageBooking = async (bookingId, checkInDate, checkOutDate) => {
        setFeedback('');

        try{
            const updateData = {
                id: bookingId,
                checkInDate: checkInDate,
                checkOutDate: checkOutDate
            }
            const response = await fetch(`http://localhost:8080/api/v1/booking/update`,{
                method: 'PUT',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('authToken')}`
                },
                body: JSON.stringify(updateData)
            }
            );

            if (response.ok){
                setFeedback("Updated booking.")
                setTimeout(()=> setFeedback(''), 3000);
                setManageAction(null);
                handleDisplayBookings();
            }
        }catch(error){
            setFeedback("Something went wrong. Try again later.")
        }

    };
    return(
        <>
        {!selectedBooking && (
            <div>
                {feedback && <p>{feedback}</p>}
                <ul>
                {bookings.map((booking) => (
                    <li key={booking.id} onClick ={() => setSelectedBooking(booking)}>
                        Room: {booking.roomNumber} | Check in: {booking.checkInDate} | Check out: {booking.checkOutDate}
                    </li>
                ))}
                </ul>
            </div>
            )}
        {selectedBooking && (
            <div> 
                <h3>Booking Details</h3>
                <p>Room: {selectedBooking.roomNumber}</p>
                <p>Check in: {selectedBooking.checkInDate}</p>
                <p>Check out: {selectedBooking.checkOutDate}</p>
                <button onClick={() =>setManageAction('cancel')}>Cancel booking</button>
                <button onClick={() =>setManageAction('modify')}>Manage booking</button>
                <button onClick={() => {setSelectedBooking(null); handleDisplayBookings();}}>Back</button>

                {manageAction === 'cancel' && (
                    <div> 
                        <p>Are you sure you want to cancel your booking?</p>
                        <button onClick={() => handleCancelBooking(selectedBooking.id)}>Yes</button>
                        <button onClick={() => {setManageAction(null);}}>No</button>
                    </div>
                )}

                {manageAction === 'modify' && (
                    <div>
                        <label>
                            Check in:
                            <input type="datetime-local" 
                            value={newCheckIn} 
                            onChange={(e) => setNewCheckIn(e.target.value)}/>
                        </label>

                        <label>
                            Check out:
                            <input type="datetime-local" value={newCheckOut} onChange={(e) => setNewCheckOut(e.target.value)}/>
                        </label>
                        <button onClick={() => handleManageBooking(selectedBooking.id,newCheckIn,newCheckOut)}>Confirm</button>
                        <button onClick={()=> setManageAction(null)}>Cancel</button>
                    </div>
                )}
            </div>
            )}
        
        </>
        );
}

export default ManageBookings