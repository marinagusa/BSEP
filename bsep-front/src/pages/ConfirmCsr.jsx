import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { confirmCsr, findCsrById } from "../service/requestService";

function ConfirmCsr(){

    const csrId = useParams().csrId;
    const [csr, setCsr] = useState({});

    useEffect(() => {
        findCsrById(csrId, response => {setCsr(response.data); console.log(response.data);})
    }, [csrId]);

    function handleConfirm(e){
        e.preventDefault();
        confirmCsr(csrId, () => {
            window.location.replace("/csrs");
        });
    }

    return(
        <div>
            <h1>Confirmation page</h1>
            <h4>CN= {csr.commonName}</h4>
            <h4>OU= {csr.organizationUnit}</h4>
            <h4>O= {csr.organizationName}</h4>
            <h4>L= {csr.localityName}</h4>
            <h4>ST= {csr.stateName}</h4>
            <h4>GN= {csr.givenName}</h4>
            <h4>SURNAME= {csr.surname}</h4>
            <button className="btn btn-primary" onClick={handleConfirm}>Confirm information</button>
        </div>
    )
    
}

export default ConfirmCsr;