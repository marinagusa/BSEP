import { useState, useEffect } from "react";
import { getAliases, getRevoked } from "../service/requestService";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";

function CertificateReviewPage(){

    const [aliases, setAliases] = useState([]);
    const [revoked, setRevoked] = useState([]);

    const navigate = useNavigate();
    
    useEffect(() => {
        getAliases(response => {
            setAliases(response.data);
        });
        getRevoked(response => {
            setRevoked(response.data);
        });
    }, []);

    function handleDetails(alias){
        navigate(`/cert-details/${alias}`);   
    }

    return(
        <div>
            <Navbar />
            <h1>Certificates</h1>
            <table style={{margin: "auto"}}>
                <thead>
                    <tr>
                        <th>Alias</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        aliases.map(a => {
                            return <tr key={a}><td>{a}</td><td><button className="btn btn-outline-primary" onClick={() => handleDetails(a)}>Details</button></td></tr>
                        })
                    }
                    {
                        revoked.map(r => {
                            return <tr key={r.id}><td>{r.alias}</td><td>Revoked</td></tr>
                        })
                    }
                </tbody>
            </table>
        </div>
        
    )
}

export default CertificateReviewPage;