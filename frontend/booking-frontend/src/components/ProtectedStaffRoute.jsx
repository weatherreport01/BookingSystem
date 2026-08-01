import { Outlet, Navigate } from "react-router-dom";

function ProtectedStaffRoute (){
    const token = localStorage.getItem('authToken');

    // need to check if the user is an admin or staff member
    const payload = JSON.parse(atob(token.split('.')[1]));
    
   
    return payload.role ==="STAFF" ? <Outlet/> : <Navigate to="/"/>
    
}

export default ProtectedStaffRoute