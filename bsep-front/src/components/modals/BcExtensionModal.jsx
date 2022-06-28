import { useState } from "react";

function BcExtensionModal(props){

    const callback = props.cb;

    const [ca, setCa] = useState(false);
    const [critical, setCritical] = useState(true);

    function handleSave(e){
        e.preventDefault();
        //callback({type: "bc", critical, ca});
    }

    return(
        <div>
            <h3>Basic Constraints</h3>
            <input type="checkbox" onChange={() => {setCa(!ca);}}/> <label>Subject is CA</label>
            <br />
            <p>Is critical?</p>
            <input type="radio" name="template" value="yes" onClick={() => setCritical(true)} defaultChecked/>
            <label style={{marginRight: "10px"}}>Yes</label>
            <input type="radio" name="template" value="no" onClick={() => setCritical(false)}/>
            <label style={{marginRight: "10px"}}>No</label>
            <br />
            <button onClick={handleSave}>Save</button>
        </div>
    )

}

export default BcExtensionModal;