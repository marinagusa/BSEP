import { useState } from "react";

function SkiExtensionModal(props){
    const callback = props.cb;

    const [method, setMethod] = useState(160);
    const [critical, setCritical] = useState(false);

    function handleSave(e){
        e.preventDefault();
        callback({type: "ski", critical, method});//ca
    }

    return(
        <div>
            <h3>Subject Key Identifier</h3>
            <label>Generation method</label> <br />
            <input type="radio" name="method" value="160" onClick={() => setMethod(160)} defaultChecked/>
            <label style={{marginRight: "10px"}}>160-bit hash</label>
            <input type="radio" name="method" value="64" onClick={() => setMethod(64)}/>
            <label style={{marginRight: "10px"}}>64-bit hash</label>
            <br />
            <p>Is critical?</p>
            <input type="radio" name="template" value="yes" onClick={() => setCritical(true)} />
            <label style={{marginRight: "10px"}}>Yes</label>
            <input type="radio" name="template" value="no" onClick={() => setCritical(false)} defaultChecked/>
            <label style={{marginRight: "10px"}}>No</label>
            <br />
            <button onClick={handleSave}>Save</button>
        </div>
    )
}
export default SkiExtensionModal;