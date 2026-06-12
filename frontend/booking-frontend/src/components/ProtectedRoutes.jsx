import { Outlet, Navigate } from "react-router-dom";

function ProtectedRoutes (){
    const token = localStorage.getItem('authToken');
    return token ? <Outlet/> : <Navigate to="/login"/>
}

export default ProtectedRoutes