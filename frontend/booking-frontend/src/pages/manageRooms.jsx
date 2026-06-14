import { useState } from "react";


// user must be signed in to access this page
function ManageRooms(){
    const [feedback, setFeedback] = useState('');
    const [rooms,setRooms] = useState();
    const token = localStorage.getItem('authToken');



    const handleDisplayBookings = async () => {
        try{
            const response = await fetch(`http://localhost:8080/api/v1/booking/currentBookings`,{
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
                }
            );

            if(response.ok){
                const data = await response.json();
                setRooms = data;
            }

        } catch(error) {
            setFeedback("Something went wrong. Try again later.")
        };
    };

    return(
        <div>
            
        </div>
        );
}

export default ManageRooms