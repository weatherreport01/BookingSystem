import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login (){

    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");
    const [feedback,setFeedback] = useState("");
    const navigate = useNavigate();

    const handleLogin = async(e) =>{
        e.preventDefault();
        const loginData = {
            email: email,
            password: password
        };
        try{
            const response = await fetch(`http://localhost:8080/api/v1/auth/signin`, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(loginData)
            });

            if (response.ok){
                setFeedback("");
                const token = await response.text();
                localStorage.setItem('authToken',token);
                setEmail("");
                setPassword("");
                navigate("/booking");
            }
            else{
                setFeedback("Incorrect email or password!");
            }
        }catch(error){
            setFeedback("Login failed. Try again later!");
        }
    }

    return(
        <div>
            <h1>Login</h1>
            <form onSubmit={handleLogin}>
                <label>Email:</label>
                <input type="email" value={email} onChange={(e) => setEmail(e.target.value)}></input>
                <label>Password:</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)}></input>
                <button type="submit" disabled={!email || !password}>Submit</button>
            </form>
            {feedback && <p>{feedback}</p>}
        </div>
    );
}

export default Login