import { Outlet, Navigate } from "react-router-dom";

function ProtectedStaffRoute (){
    const token = localStorage.getItem('authToken');

    // need to check if the user is an admin or staff member
    return <></>
}

export default ProtectedRoute