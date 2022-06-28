import { useState, useEffect } from "react";
import { deleteUser, getAllUsers, searchFilterUsers } from "../service/requestService";
import { useNavigate } from "react-router-dom"
import Navbar from "../components/Navbar";

function ViewUsersPage(){

    const [users, setUsers] = useState([]);

    const [searchFilter, setSearchFilter] = useState({enabledFilter: true});

    const navigate = useNavigate();

    useEffect(() => {
        getAllUsers(response => {
            setUsers(response.data);
        })
    }, []);
    
    function handleExpand(userId){
        navigate(`/user/${userId}`);
    }

    function handleDelete(userId){
        deleteUser(userId, () => {
            setUsers(users.filter(u => u.id !== userId));
        })
    }

    function handleRoleChange(e){
        let val = e.target.value;
        if(val === "a"){
            setSearchFilter({...searchFilter, roleFilter: {id: 1, name: "ROLE_ADMIN"}});
        }
        else if(val === "o"){
            setSearchFilter({...searchFilter, roleFilter: {id: 2, name: "ROLE_OWNER"}});
        }
        else if(val === "t"){
            setSearchFilter({...searchFilter, roleFilter: {id: 3, name: "ROLE_TENANT"}});
        }
    }

    function handleSearch(){
        console.log(searchFilter);
        searchFilterUsers(searchFilter, response => {
            setUsers(response.data);
        })
    }

    return(
        <div>
            <Navbar />
            <h1>Users</h1>
            <hr />
            <br />
            <label>Search: </label>
            <input type="text" className="form-control" style={{maxWidth: "50%", margin: "auto"}} onChange={(e) => setSearchFilter({...searchFilter, search: e.target.value})}/>
            <label>Role:</label>
            <select className="form-control" onChange={handleRoleChange} style={{maxWidth: "50%", margin: "auto"}}>
                <option>Select role</option>
                <option value="a">Admin</option>
                <option value="o">Owner</option>
                <option value="t">Tenant</option>
            </select>
            <label>Enabled:</label> <br />
            <input type="radio" value="y" name="enabled" defaultChecked onClick={() => setSearchFilter({...searchFilter, enabledFilter: true})}/> <label>Yes</label>
            <input type="radio" value="n" name="enabled" onClick={() => setSearchFilter({...searchFilter, enabledFilter: false})}/> <label>No</label>
            <br />
            <button className="btn btn-outline-primary" onClick={handleSearch}>Search</button>
            <hr />
            <br />

            <table className="table table-striped" style={{maxWidth: "80%", margin: "auto"}}>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Username</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>
                        <th>Enabled</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map(u => {
                            return <tr key={u.id}>
                                    <td>{u.id}</td><td>{u.username}</td><td>{u.firstName}</td><td>{u.lastName}</td><td>{u.email}</td><td>{String(u.enabled)}</td>
                                    <td><button className="btn btn-outline-primary" onClick={() => handleExpand(u.id)}>Expand</button></td>
                                    <td><button className="btn btn-outline-danger" onClick={() => handleDelete(u.id)}>Delete</button></td>
                                    </tr>
                        })
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ViewUsersPage;