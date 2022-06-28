import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";
import { getCertInfo, revokeCertificate } from "../service/requestService";

function RevokeCert(){

    const alias = useParams().alias;

    const [resp, setResp] = useState({});

    const[reason, setReason] = useState('');

    useEffect(() => {
        console.log(alias);
        getCertInfo(alias, response => { 
            setResp(response.data);
            console.log(response.data);
        }, err => {
            console.log(err);
        })
    }, [alias]);

    function handleRevoke(sn){
        console.log(sn);
        revokeCertificate(sn, reason, response => {
            alert("Certificate Revoked!");
        }, err => {
            alert("Enter reason before revoking certificate!");
        })
    }

    function parseDn(dn){
        if(dn){
            let tokens = dn.split(',');
            return String(tokens.filter(t => t[0].charAt(0).match(/[a-zA-Z]/)));
        }
    }

    return(
        <div>
            <Navbar />
            <h1>Certificate {resp.serialNumber} details</h1>
            <h5>Issuer: {resp.issuerDn?.name} </h5>
            <h5>Subject: {parseDn(resp.subjectDn?.name)} </h5>
            <h5>Valid: {String(resp.valid)} </h5>
            <label>Revoke reason</label>
            <textarea className="form-control" id="exampleFormControlTextarea1" rows="3" onChange={(e) => {setReason(e.target.value)}} style={{maxWidth:"50%", margin:"auto"}}></textarea>
            <button className="btn btn-primary" onClick={() => handleRevoke(resp.serialNumber)} style={{marginTop:"20px"}}>Revoke</button>
        </div>
    )
}

export default RevokeCert;