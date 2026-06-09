import { Link } from 'react-router-dom';

function NavBar(){
    return(
        <>
            <Link to="/">
                <button>Home</button>
            </Link>
            <Link to="/booking">
                <button>Booking</button>
            </Link>
        </>
    );
}

export default NavBar