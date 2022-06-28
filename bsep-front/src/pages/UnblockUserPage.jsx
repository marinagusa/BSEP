import { useState, useEffect } from "react";
import { changeUserRole, enableUser, findUserById } from "../service/requestService";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";

function UnblockUserPage(){

    const [user, setUser] = useState({});
    const userId = useParams().userId;

    useEffect(() => {
        findUserById(userId, (response) => {
            console.log(response.data);
            setUser(response.data);
        });
    }, [userId]);

    function handleEnableUser(){
        enableUser(userId, response => { setUser(response.data) });
    }

    function handleRoleChange(e){
        let val = e.target.value;
        console.log(user);
        if(val === "a"){
            changeUserRole(userId, 1, response => setUser(response.data));
        }
        else if(val === "o"){
            changeUserRole(userId, 2, response => setUser(response.data));
        }
        else if(val === "t"){
            changeUserRole(userId, 3, response => setUser(response.data));
        }
    }
    return(
        <div>
            <Navbar />
            <h4>{user.username}</h4>
            <h4>{user.name}</h4>
            <h4>{user.email}</h4>
            <h4>{user.roles?.length > 0 ? user.roles[0].name : ""}</h4>
            <select className="form-control" onChange={handleRoleChange} style={{maxWidth: "50%", margin: "auto"}}>
                <option>Edit role</option>
                <option value="a">Admin</option>
                <option value="o">Owner</option>
                <option value="t">Tenant</option>
            </select>
            {!user.enabled && <button className="btn btn-primary" onClick={handleEnableUser}>Enable user</button>}
        </div>
    );
}

export default UnblockUserPage;