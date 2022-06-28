import { useState } from "react";

function KuExtensionModal(props){
    const callback = props.cb;

    const [usages, setUsages] = useState([]);
    const [critical, setCritical] = useState(true);

    function handleSave(e){
        e.preventDefault();
        callback({type: "ku", critical, usages});
    }

    return(
        <div>
            <h3>Key usage</h3>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Certificate signing'])}}/> <label>Certificate Signing</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Decipher only'])}}/> <label>Decipher Only</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Key agreement'])}}/> <label>Key Agreement</label>
            <br />
            <input type="checkbox" onChange={() => {setUsages([...usages, 'CRL sign'])}}/> <label>CRL Sign</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Digital signature'])}} defaultChecked/> <label>Digital Signature</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Key encipherment'])}} defaultChecked/> <label>Key Encipherment</label>
            <br />
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Data encipherment'])}}/> <label>Data Encipherment</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Encipher only'])}}/> <label>Encipher Only</label>
            <input type="checkbox" onChange={() => {setUsages([...usages, 'Non repudiation'])}}/> <label>Non Repudiation</label>
            <br />
            <p>Is critical?</p>
            <input type="radio" name="template" value="yes" onClick={() => setCritical(true)} defaultChecked/>
            <label style={{marginRight: "10px"}}>Yes</label>
            <input type="radio" name="template" value="no" onClick={() => setCritical(false)} />
            <label style={{marginRight: "10px"}}>No</label>
            <br />
            <button onClick={handleSave}>Save</button>
        </div>
    )
}

export default KuExtensionModal;