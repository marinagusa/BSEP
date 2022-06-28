import { useState } from "react";

function EkuExtensionModal(props){
    const callback = props.cb;

    const [usages, setUsages] = useState(['TLS wsa']);
    const [critical, setCritical] = useState(false);

    function handleSave(e){
        e.preventDefault();
        callback({type: "eku", critical, usages});
    }

    return(
        <div>
            <h3>Extended key usage</h3>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Any Eku'])}}/> <label>Any Extended Key Usage</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Code signing'])}}/> <label>Code Signing</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'IPTT'])}}/> <label>IP Tunnel Termination</label>
            <br />
            <input type="checkbox" onChange={() => {setUsages([...usages, 'E-mail protection'])}}/> <label>Email protection</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'IP es'])}}/> <label>IP Security End System</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'IP su'])}}/> <label>IP Security User</label>
            <br />
            <input type="checkbox" onChange={() => {setUsages([...usages, 'OCSP'])}}/> <label>OCSP Signing</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'TLS wca'])}}/> <label>TLS Web Client Auth</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'TLS wsa'])}} defaultChecked/> <label>TLS Web Server Auth</label>
            <br />
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Time stamping'])}}/> <label>Time stamping</label>

            <p>Is critical?</p>
            <input type="radio" name="template" value="yes" onClick={() => setCritical(true)}/>
            <label style={{marginRight: "10px"}}>Yes</label>
            <input type="radio" name="template" value="no" onClick={() => setCritical(false)} defaultChecked/>
            <label style={{marginRight: "10px"}}>No</label>
            <br />
            <button onClick={handleSave}>Save</button>
        </div>
    )
}

export default EkuExtensionModal;