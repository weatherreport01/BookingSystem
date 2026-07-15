import { useState } from "react";


function staffMenu(){

    const [searchType,setSearchType] = useState("Member");
    const [searchText, setSearchText] = useState();
    const [feedback, setFeedback] = useState("");
    const [searchData, setSearchData] = useState();
    const [selectedMember, setSelectedMember] = useState();
    const [selectedRoom, setSelectedRoom] = useState();

    const handleSearch = async => {
        setFeedback("");
        if (searchText ==="Member"){
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
        else if (searchText ==="Room"){
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
        else if (searchText ==="Booking"){
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

    const handleDisplayMemberInfo = async => {
        // need to make it display all info about a selected member
    };

    
    // could include a delete member option?

    const handleCreateRoom = async => {

    };

    const handleUpdateRoom = async => {

    };

    const handleDeleteRoom = async => {

    };



    return(<>
        {!selectedMember && (
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
            </div>
            )};

            {selectedMember && (
                <div>
                    <h3>Member Details:</h3>
                    <p>Full Name: {selectedMember.name}</p>
                    <p>Email: {selectedMember.email}</p>
                    <button>Delete Member</button>
                    <button onClick={()=> {setSelectedMember(null);}}>Back</button>
                </div>
            )};

            {selectedRoom && (
                <div>
                    
                </div>
            )};
          </>);
}

export default staffMenu;