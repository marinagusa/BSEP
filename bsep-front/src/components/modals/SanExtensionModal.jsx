import { useState } from "react";

function SanExtensionModal(props){

    const callback = props.cb;

    const [directory, setDirectory] = useState('');
    const [dns, setDns] = useState('');
    const [ipAddress, setIpAddress] = useState('');
    const [rfc, setRfc] = useState('');
    const [uri, setUri] = useState('');
    const [upn, setUpn] = useState('');
    
    const [critical, setCritical] = useState(false);

    function handleSave(e){
        e.preventDefault();
        callback({type: "san", critical, directory, dns, ipAddress, rfc, uri, upn});
    }

    return(
        <div>
            <h3>Subject Alternative Name</h3>
            <label>Directory Name</label> <input type="text" onChange={(e) => {setDirectory(e.target.value)}}/>
            <br />
            <label>DNS Name</label> <input type="text" onChange={(e) => {setDns(e.target.value)}}/>
            <br />
            <label>IP Address</label> <input type="text" onChange={(e) => {setIpAddress(e.target.value)}}/>
            <br />
            <label>RFC 822 Name</label> <input type="text" onChange={(e) => {setRfc(e.target.value)}}/>
            <br />
            <label>URI</label> <input type="text" onChange={(e) => {setUri(e.target.value)}}/>
            <br />
            <label>UPN</label> <input type="text" onChange={(e) => {setUpn(e.target.value)}}/>
            <br />
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

export default SanExtensionModal;