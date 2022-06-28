import { Link, useNavigate } from "react-router-dom";
import { logout } from "../service/requestService";

function Navbar(){

    const navigate = useNavigate();

    function handleLogout(){
        logout(() => {
            sessionStorage.clear();
            navigate("/");
        })
    }
    return(
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <Link to="/csrs" className="navbar-brand">Certificate Requests</Link>
            <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link to="/certificate" className="nav-link">Certificates</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/create-csr" className="nav-link">New CSR</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/users" className="nav-link">Users</Link>
                    </li>
                    <li className="nav-item">
                        <button className="btn btn-link nav-link" onClick={handleLogout}>Logout</button>
                    </li>
                </ul>
            </div>
        </nav>
    )
}

export default Navbar;