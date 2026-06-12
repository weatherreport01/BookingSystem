import { useState } from "react"
import { useNavigate } from "react-router-dom";

function Signup (){
    const [email,setEmail] = useState("");
    const [name, setName] = useState("");
    const [password,setPassword] = useState("");
    const [feedback,setFeedback] = useState("");
    const navigate = useNavigate();

    const handleSignup = async (e)=> {
        e.preventDefault();

        const signupData = {
            name: name,
            email: email,
            password: password
        };
        try{
            const response = await fetch(`http://localhost:8080/api/auth/signup`, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(signupData)
            });

            if (response.ok){
                setFeedback(`${await response.text()}`);
                setName("");
                setEmail("");
                setPassword("");
                setTimeout(()=> setFeedback(''),3000);
                navigate("/login");
            }
            else if (response.status === 409){
                setFeedback("Email already in use! Try a different one.");
            }
            else{
                setFeedback("Something went wrong. Try again later!");
            }
        } catch(error){
            setFeedback("Something went wrong. Try again later!");
        }
    }

    return(
        <div>
            <h1>Signup</h1>
            <form onSubmit={handleSignup}>
                <label>Name:</label>
                <input type="text" value={name} onChange={(e) => setName(e.target.value)}></input>
                <label>Email:</label>
                <input type="email" value={email} onChange={(e) => setEmail(e.target.value)}></input>
                <label>Password:</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)}></input>
                <button type="submit" disabled={ !name || !email || !password}>Submit</button>
            </form>
            {feedback && <p>{feedback}</p>}
        </div>
    );
}

export default Signup