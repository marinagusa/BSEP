import { useState } from "react";
import Navbar from "../components/Navbar";
import { addCsr } from "../service/requestService";

function CreateCsrPage(){

    const [email, setEmail] = useState('');
    const [commonName, setCommonName] = useState('');
    const [organizationUnit, setOrganizationUnit] = useState('');
    const [organizationName, setOrganizationName] = useState('');
    const [localityName, setLocalityName] = useState('');
    const [stateName, setStateName] = useState('');
    const [country, setCountry] = useState('');
    const [givenName, setGivenName] = useState('');
    const [surname, setSurname] = useState('');
    const [userId, setUserId] = useState('');

    function handleSend(e){
        e.preventDefault();
        addCsr({email, commonName, organizationUnit, organizationName, localityName, stateName, country, givenName, surname, userId}, (response) => {
            alert('CSR saved, check your email');
        }, (error) => { console.log(error) });
    }

    return(
        <div>
        <Navbar />
        <div style={{maxWidth: "40%", margin: 'auto'}}>
            <h1>Create CSR</h1>
            <br />
            <div className="form-outline mb-4" style={{marginTop: '40px'}}>
                <input type="text" id="form2Example1" className="form-control" onChange={(e) => setEmail(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example1">EMAIL</label>
            </div>

            <div className="form-outline mb-4">
                <input type="text" id="form2Example2" className="form-control" onChange={(e) => setCommonName(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">CN</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example3" className="form-control" onChange={(e) => setOrganizationUnit(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">OU</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example3" className="form-control" onChange={(e) => setOrganizationName(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">O</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example4" className="form-control" onChange={(e) => setLocalityName(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">L</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example5" className="form-control" onChange={(e) => setStateName(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">ST</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example6" className="form-control" onChange={(e) => setCountry(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">C</label>
            </div>
            
            <div className="form-outline mb-4">
                <input type="text" id="form2Example6" className="form-control" onChange={(e) => setGivenName(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">GN</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example6" className="form-control" onChange={(e) => setSurname(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">SURNAME</label>
            </div>
            <div className="form-outline mb-4">
                <input type="text" id="form2Example6" className="form-control" onChange={(e) => setUserId(e.target.value)}/>
                <label className="form-label" htmlFor="form2Example2">UID</label>
            </div>
            <button type="button" className="btn btn-primary btn-block mb-4" onClick={handleSend}>Send</button>
        </div>
        </div>
    )
}

export default CreateCsrPage;