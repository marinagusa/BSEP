import { useState } from "react";
import { register } from "../service/requestService";
import { useNavigate } from "react-router-dom";

function Register(){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [passwordConfirm, setPasswordConfirm] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState({});

    const navigate = useNavigate();

    function handleSignUp(e){
        e.preventDefault();
        console.log({username, password, firstName, lastName, email, roles: [role]});
        register({username, password, firstName, lastName, email, roles: [role]}, (response) => {
            console.log(response.data);
            navigate("/");
        }, (error) => { console.log(error) });
    }

    function handleRoleChange(e){
        e.preventDefault();
        if(e.target.value === "Admin")
            setRole({id: 1, name: "ROLE_ADMIN"});
        else if(e.target.value === "Owner")
            setRole({id: 2, name: "ROLE_OWNER"});
        else if(e.target.value === "Tenant")
            setRole({id: 3, name: "ROLE_TENANT"});
    }

    return(
        <div style={{maxWidth: "40%", margin: 'auto'}}>
            <h1>Register page</h1>
            <br />
            <div className="form-outline mb-4" style={{marginTop: '40px'}}>
                <input type="text" id="form2Example1" className="form-control" onChange={(e) => setUsername(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example1">Username</label>
            </div>

            <div className="form-outline mb-4">
                <input type="password" id="form2Example2" className="form-control" onChange={(e) => setPassword(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">Password</label>
            </div>
            <div className="form-outline mb-4">
                <input type="password" id="form2Example3" className="form-control" onChange={(e) => setPasswordConfirm(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">Confirm password</label>
            </div>
            {password !== passwordConfirm && <p style={{color: "red"}}>Passwords do not match</p>}
            <div className="form-outline mb-4">
                <input type="text" id="form2Example4" className="form-control" onChange={(e) => setFirstName(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">First name</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example5" className="form-control" onChange={(e) => setLastName(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">Last name</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example6" className="form-control" onChange={(e) => setEmail(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">Email</label>
            </div>
            
            <div className="form-outline mb-4">
                <select id="form2Example7" className="form-control" onChange={handleRoleChange}>
                    <option>Choose role</option>
                    <option>Admin</option>
                    <option>Owner</option>
                    <option>Tenant</option>
                </select>
                <label className="form-label" htmlFor="form2Example2">Role</label>
            </div>

            <button type="button" className="btn btn-primary btn-block mb-4" onClick={handleSignUp}>Sign up</button>
        </div>
    )
}

export default Register;