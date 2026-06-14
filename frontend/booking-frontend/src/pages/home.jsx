import { useState } from "react";
import { Link } from "react-router-dom";
function Home(){
    // probably needs to have more content
    const [token,setToken] = useState (localStorage.getItem('authToken'));
    return(<div>

            <h1>Welcome to the our room booking site!</h1>
            {!token && (<>
                <p>You need to login in to book a room.</p>
                <Link to={"/login"}>
                    <button>Click here to get started!</button>
                </Link>
            </>)}
        </div>
    );
}

export default Home