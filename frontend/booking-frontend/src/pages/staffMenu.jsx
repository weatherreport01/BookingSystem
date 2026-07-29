import { use, useState } from "react";


function staffMenu(){

    const [searchType,setSearchType] = useState("Member");
    const [searchText, setSearchText] = useState();
    const [feedback, setFeedback] = useState("");
    const [searchData, setSearchData] = useState();
    const [selectedMember, setSelectedMember] = useState();
    const [selectedRoom, setSelectedRoom] = useState();
    const [showUpdateForm, setShowUpdateForm] = useState(false);
    const [selectedBooking, setSelectedBooking] = useState();
    const [newRoomNumber,setNewRoomNumber] = useState();
    const [newRoomType, setNewRoomType] = useState();

    const handleSearch = async () => {
        setFeedback("");
        if (searchType ==="Member"){
            try{
                const response = await fetch(`http://localhost:8080/api/v1/members/search?name=${searchText}`,{
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('authToken')}`
                }

            });
            if (response.ok){
                const data = await response.json();
                setSearchData(data);
                setFeedback("Member(s) Found!");
                setTimeout(()=> setFeedback(''),3000); 
            }
            } catch(error){
                setFeedback("Something went wrong!");
            };
            
        }
        else if (searchType ==="Room"){
            try{
                const response = await fetch(`http://localhost:8080/api/v1/rooms/searchByRoomNumber?roomNumber=${searchText}`,{
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('authToken')}` 
                }

            });
            if (response.ok){
                const data = await response.json();
                setSearchData(data);
                setFeedback("Room(s) Found!");
                setTimeout(()=> setFeedback(''),3000);
            }
            } catch(error){
                setFeedback("Something went wrong!");
            };
        }
        else if (searchType === "Booking"){
            try{
                // need to make staff controller that will display all current bookings
                const response = await fetch(`http://localhost:8080/api/v1/`,{
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('authToken')}`
                }

            });
            if (response.ok){
                const data = await response.json();
                setSearchData(data);
                setFeedback("Booking(s) Found!");
                setTimeout(()=> setFeedback(''),3000);
            }
            } catch(error){
                setFeedback("Something went wrong!");
            };
        }

    };

    const handleDisplayMemberInfo = async () => {
        // need to make it display all info about a selected member
    };

    
    // could include a delete member option?
    // + delete/ban? member
    // + maybe staff notes for members and bookings could be useful

    const handleCreateRoom = async () => {

    };

    const handleUpdateRoom = async (roomNumber,newRoomNumber, type) => {
        // simply send the update to the backend 
        // remember to convert the the roomnumber to an int - check in other places if this needs to be changed too
        try{

            const updateData = {
                roomNumber: roomNumber,
                newRoomNumber: newRoomNumber,
                type: type
            }
            const response = await fetch(`http://localhost:8080/api/v1/staff/rooms/update`,{
                method: 'PUT',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('authToken')}`
                },
                body: JSON.stringify(updateData)
                }
            );
            if (response.ok){
                setFeedback("Updated room!")
                // etc
                
            }

        } catch(error){
            // do smth
        };

    };

    const handleDeleteRoom = async (roomNumber) => {
        // roomNumber should be provided

        try{
            const response = await fetch(`http://localhost:8080/api/v1/staff/rooms/delete`,{
                method: 'DELETE',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('authToken')}`},
                body: JSON.stringify({id:roomNumber})
                }
            );

            if(response.ok){
                setFeedback("Room Deleted!");
                
            }
            else{
                setFeedback("Something went wrong. Try again.");
            }

        } catch(error){
            // do something
        };
    };



    return(
        <>
        {!selectedMember && !selectedBooking && !selectedRoom && (
            <div>
                <h1>Management Page</h1>
                <h2>Search for a member, room or booking</h2>

                <label>
                    <input type="radio" name="searchType" value="Member" checked={searchType==="Member"} 
                     onChange={(e) => setSearchType(e.target.value)}/>
                     Member
                </label>
                <label>
                    <input type="radio" name="searchType" value="Room" checked={searchType==="Room"} 
                     onChange={(e) => setSearchType(e.target.value)}/>
                     Room
                </label>
                <label>
                    <input type="radio" name="searchType" value="Booking" checked={searchType==="Booking"} 
                     onChange={(e) => setSearchType(e.target.value)}/>
                     Booking
                </label>

                <label>
                    <input type="text" value={searchText} onChange={(e) => setSearchText(e.target.value)}/>
                </label>

                <button onClick={handleSearch} disabled={!searchText}>Search</button>

                {searchData && (
                    searchData.map((data) => (
                    <div key={data.id}>
                    {searchType === "Room" && (
                        <div onClick={() => setSelectedRoom(data)}>
                            <p>Room Number: {data.roomNumber}</p>
                            <p>Type: {data.type}</p>
                        </div>
                    )}
                    {searchType === "Member" && (
                        <div onClick={() => setSelectedMember(data)}>
                            <p>Full Name: {data.name}</p>
                            <p>Email: {data.email}</p>
                        </div>
                    )}
                    {searchType === "Booking" && (
                        <div onClick={() => setSelectedBooking(data)}> 
                            <p>Booking ID: {data.id}</p>
                            <p>Room Number: {data.roomNumber}</p>
                            <p>Member ID: {data.memberId}</p>
                            <p>Check in: {data.checkInDate}</p>
                            <p>Check out: {itedatam.checkOutDate}</p>
                            <p>Status: {data.status}</p>
                        </div>
                    )}
                        
                    </div>
                )))}
            </div>
        )}
        
           
        {selectedMember && (
            <div>
                <h3>Member Details:</h3>
                <p>Full Name: {selectedMember.name}</p>
                <p>Email: {selectedMember.email}</p>
                <button>Delete Member</button>
                <button onClick={() => setSelectedMember(null)}>Back</button>
            </div>
        )}

        {selectedRoom && (
            <div>
                <h3>Room Details: </h3>
                <p>Room ID: {selectedRoom.id}</p>
                <p>Room Number: {selectedRoom.roomNumber}</p>
                <p>Type: {selectedRoom.type}</p>
                <button onClick={() => setSelectedRoom(null)}>Back</button>
                <button onClick={() => setShowUpdateForm(true)}>Update Details</button>

                {showUpdateForm &&(
                    <div>
                        <h3>Update Details Below:</h3>
                        <label>
                            Room Number:
                            <input type="number"
                                value={selectedRoom.roomNumber}
                                onChange={(e) => setNewRoomNumber(e.target.value)}/>
                        </label>

                        <label>
                            Type:
                            <select value={selectedRoom.type} onChange={(e) => setNewRoomType(e.target.value)}>
                                {roomTypes.map((type) => (
                                    <option key={type} value={type}>{type}</option>
                                ))}
                            </select>
                        </label>
                        <button onClick={() => handleUpdateRoom()}>Confirm</button>
                    </div>  
                )}
            </div>

        
        )}
        </>);
}

export default staffMenu;