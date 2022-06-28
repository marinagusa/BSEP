import { useEffect, useState } from "react";
import { deleteCsr, getCsrs } from "../service/requestService";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
function ViewCsrs(){

    const [csrs, setCsrs] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        getCsrs(response => {
            setCsrs(response.data);
        }, err => {
            console.log(err);
        });
    }, []);

    function handleApprove(csrId){
        navigate(`/issue-cert/${csrId}`);
    }

    function handleDelete(csrId){
        deleteCsr(csrId, response => {
            if(response.data){
                setCsrs(csrs.filter(csr => csr.id !== csrId));
            }
        })
    }

    return (
        <div>
            <Navbar />
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Email</th>
                        <th>Common Name</th>
                        <th>Organization Unit</th>
                        <th>Organization Name</th>
                        <th>Locality Name</th>
                        <th>State Name</th>
                        <th>Country</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        csrs.map(csr => {
                            return <tr key={csr.id}><td>{csr.id}</td><td>{csr.email}</td><td>{csr.commonName}</td><td>{csr.organizationUnit}</td><td>{csr.organizationName}</td>
                                    <td>{csr.localityName}</td><td>{csr.stateName}</td><td>{csr.country}</td>
                                    <td><button className="btn btn-outline-primary" onClick={() => handleApprove(csr.id)}>Approve</button><button className="btn btn-outline-danger" onClick={() => handleDelete(csr.id)}>Delete</button></td></tr>
                        })
                    }
                </tbody>
            </table>
        </div>
    )

}

export default ViewCsrs;