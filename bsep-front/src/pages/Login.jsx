import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { login } from "../service/requestService";

function Login(){

    const[username, setUsername] = useState('');
    const[password, setPassword] = useState('');

    const navigate = useNavigate();

    function handleSignIn(e){
        e.preventDefault();
        login({username, password}, (response) => {
            sessionStorage.setItem("jwt", response.data.accessToken);
            sessionStorage.setItem("validUntil", new Date(Date.now() + response.data.expiresIn));
            navigate("/csrs");
        }, (error) => { console.log(error); })
    }

    return(
        <div style={{maxWidth: "40%", margin: 'auto'}}>
            <h1>Login page</h1>
            <br />
            <div className="form-outline mb-4" style={{marginTop: '40px'}}>
                <input type="text" id="form2Example1" className="form-control" onChange={(e) => setUsername(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example1">Username</label>
            </div>

            <div className="form-outline mb-4">
                <input type="password" id="form2Example2" className="form-control" onChange={(e) => setPassword(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">Password</label>
            </div>

            <div className="row mb-4">
                <div className="col d-flex justify-content-center">
                <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="form2Example31" defaultChecked />
                    <label className="form-check-label" htmlFor="form2Example31"> Remember me </label>
                </div>
                </div>
            </div>

            <button type="button" className="btn btn-primary btn-block mb-4" onClick={handleSignIn}>Sign in</button>

            <div className="text-center">
                <p>Not a member? <Link to="/register">Register</Link></p>
            </div>
        </div>
    )
}
export default Login;