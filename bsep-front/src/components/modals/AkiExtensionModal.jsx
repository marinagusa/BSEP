import { useState } from "react";

function AkiExtensionModal(props){

    const [acsn, setAcsn] = useState('');
    const [critical, setCritical] = useState(false);

    const callback = props.cb;

    function handleSave(e){
        e.preventDefault();
        callback({type: "aki", critical, acsn});//acsn
    }

    return(
        <div>
            <h3>Authority Key Identifier</h3>
            <input type="text" placeholder="Authority Cert Serial Number" onChange={(e) => {setAcsn(e.target.value);}} style={{maxWidth: "80%"}}/>
            <br />
            <p>Is critical?</p>
            <input type="radio" name="template" value="yes" onClick={() => setCritical(true)}/>
            <label style={{marginRight: "10px"}}>Yes</label>
            <input type="radio" name="template" value="no" onClick={() => setCritical(false)} defaultChecked/>
            <label style={{marginRight: "10px"}}>No</label>
            <br />
            <button onClick={handleSave}>Save</button>
        </div>
    );
}

export default AkiExtensionModal;