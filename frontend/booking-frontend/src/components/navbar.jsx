import { Link } from 'react-router-dom';
import { useState } from 'react';
function NavBar(){

    const [token,setToken] = useState (localStorage.getItem('authToken'));

    const handleLogout = () =>{
        localStorage.removeItem('authToken');
        setToken(null);
    }
    return(
        <>
            <Link to="/">
                <button>Home</button>
            </Link>
            <Link to="/booking">
                <button>Booking</button>
            </Link>
            <Link to="/manage">
                <button>Manage Bookings</button>
            </Link>
            {token && <button onClick={handleLogout}>Logout</button>}
            {!token && (
                <Link to="/login">
                    <button>Login</button>
                </Link>
            )}
        </>
    );
}

export default NavBar