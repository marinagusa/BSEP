import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ReactDatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import ModalBase from "../components/ModalBase";
import { findCsrById, issueCertificate } from "../service/requestService";
import Navbar from "../components/Navbar";

function IssueCertificatePage(){

    const csrId = useParams().csrId;

    const [issuerAlias, setIssuerAlias] = useState('inter');
    const [subjectData, setSubjectData] = useState({});
    const [start, setStart] = useState(new Date());
    const [end, setEnd] = useState(new Date().setFullYear(new Date().getFullYear() + 1));
    const [extensionList, setExtensionList] = useState([]);

    const [template, setTemplate] = useState('');
    const [extensions, setExtensions] = useState([]);
    const [toAddExt, setToAddExt] = useState([]);

    const [showModal, setShowModal] = useState(false);
    const [modalExt, setModalExt] = useState('');

    useEffect(() => {
        findCsrById(csrId, response => {
            let rd = response.data;
            setSubjectData({givenName: rd.givenName, surname: rd.surname, commonName: rd.commonName, organizationName: rd.organizationName, organizationUnit: rd.organizationUnit, country: rd.country, email: rd.email, userId: rd.userId});
        }, err => {
            console.log(err);
        })
    }, [csrId]);

    function handleIssue(e){
        e.preventDefault();
        console.log(template);
        console.log({issuerAlias, subjectData, start: convertDate(start), end: convertDate(end), extensionList});
        issueCertificate({issuerAlias, subjectData, start: convertDate(start), end: convertDate(end), extensionList},
        () => {
            alert("Certificate issued!");
        }, err => { console.log(err); })
    }

    function convertDate(date){
        let month = date.getMonth() + 1;
        let day = date.getDate();
        if(month < 10) 
            month = "0" + month;
        if(day < 10) 
            day = "0" + day;
        return `${date.getFullYear()}-${month}-${day}`;
    }

    function handleTemplateChange(e){
        setTemplate(e.target.value);
        let caExt = ['Authority Key Identifier', 'Basic Constraints', 'Key Usage','Subject Key Identifier'];
        let sslsExt = ['Authority Key Identifier', 'Extended Key Usage', 'Basic Constraints', 'Key Usage', 'Subject Alternative Name','Subject Key Identifier'];
        let sslcExt = ['Authority Key Identifier', 'Extended Key Usage', 'Key Usage', 'Subject Key Identifier'];
        if(e.target.value === 'ca')
            setExtensions(caExt);
        else if(e.target.value === 'ssls')
            setExtensions(sslsExt);
        else
            setExtensions(sslcExt);
    }

    function showExtension(ext){
        setShowModal(true);
        setModalExt(ext);
    }

    function removeExtension(ext){
        let newExtensions = extensions.filter(e => e !== ext);
        setExtensions(newExtensions);
    }

    function addExtension(){
        let allExtensions = ['Authority Key Identifier', 'Extended Key Usage', 'Basic Constraints', 'Key Usage', 'Subject Alternative Name','Subject Key Identifier'];
        let remaining = allExtensions.filter(e => !extensions.includes(e));
        setToAddExt(remaining);
    }

    function extensionCallback(extension){
        setExtensionList([...extensionList, extension]);
        console.log(extension);
    }

    return(
        <div>
        <Navbar />
        <div style={{maxWidth: "50%", margin: 'auto'}}>
            <h1>Issue certificate</h1>
            <div className="form-outline mb-4" style={{marginTop: "40px"}}>
                <select className="form-control" onChange={(e) => setIssuerAlias(e.target.value)}>
                    <option>inter</option>
                </select>
                <label className="form-label" htmlFor="form2Example2">Issuer</label>
            </div>
            <div>Subject <p>CN={subjectData.commonName} OU={subjectData.organizationUnit} O={subjectData.organizationName} L={subjectData.localityName} ST={subjectData.stateName} C={subjectData.country}</p></div>
            <div className="form-outline mb-4">
                <ReactDatePicker selected={start} onSelect ={(date) => setStart(date)} dateFormat="yyyy/MM/dd" />
                <label className="form-label" htmlFor="form2Example2">Start date</label>
            </div>
            <div className="form-outline mb-4">
                <ReactDatePicker selected={end} onSelect ={(date) => setEnd(date)} dateFormat="yyyy/MM/dd" />
                <label className="form-label" htmlFor="form2Example2">End date</label>
            </div>


            <div className="extensions-workspace">
                <input type="radio" name="template" value="ca" onClick={handleTemplateChange}/>
                <label style={{marginRight: "10px"}}>CA</label>
                <input type="radio" name="template" value="ssls" onClick={handleTemplateChange}/>
                <label style={{marginRight: "10px"}}>SSL Server</label>
                <input type="radio" name="template" value="sslc" onClick={handleTemplateChange}/>
                <label style={{marginRight: "10px"}}>SSL Client</label>
                <input type="radio" name="template" value="cs" onClick={handleTemplateChange}/>
                <label style={{marginRight: "10px"}}>Code signing</label>
            </div>

            <table style={{margin: "auto", marginTop:"20px", marginBottom: "30px"}}>
                <tbody>
                    {
                        extensions.map((e) => {
                            return <tr><td>{e}</td><td><button className="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModalCenter" onClick={() => showExtension(e)}>Expand</button></td><td><button className="btn btn-outline-danger" onClick={() => removeExtension(e)}>Remove</button></td></tr>
                        })
                    }
                </tbody>
            </table>

            {showModal && <ModalBase ext={modalExt} callback={extensionCallback}/>}

            <button className="btn btn-outline-primary" onClick={addExtension}>Add extension</button>
            {
                toAddExt && <table><tbody>{
                    toAddExt.map(ext => {
                        return <tr><td>{ext}</td><td>
                            <button className="btn btn-outline-primary" onClick={
                                () => {setExtensions([...extensions, ext]); 
                                        setToAddExt(toAddExt.filter(ta => ta !== ext));}}>Add</button></td></tr>
                })}</tbody></table>
            }

            <button type="button" className="btn btn-primary btn-block mb-4" style={{marginTop: "20px"}} onClick={handleIssue}>Issue</button>
        </div>
        </div>
    )
}

export default IssueCertificatePage;