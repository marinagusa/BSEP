import AkiExtensionModal from "./modals/AkiExtensionModal";
import BcExtensionModal from "./modals/BcExtensionModal";
import EkuExtensionModal from "./modals/EkuExtensionModal";
import KuExtensionModal from "./modals/KuExtensionModal";
import SanExtensionModal from "./modals/SanExtensionModal";
import SkiExtensionModal from "./modals/SkiExtensionModal";

function ModalBase(props){
    const ext = props.ext;
    const callback = props.callback;

    return(
        <div id="exampleModal" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-body">
                        {ext === 'Authority Key Identifier' && <AkiExtensionModal cb={callback}/>}
                        {ext === 'Basic Constraints' && <BcExtensionModal cb={callback}/> }
                        {ext === 'Key Usage' && <KuExtensionModal cb={callback}/>}
                        {ext === 'Subject Key Identifier' && <SkiExtensionModal cb={callback}/>}
                        {ext === 'Extended Key Usage' && <EkuExtensionModal cb={callback}/>}
                        {ext === 'Subject Alternative Name' && <SanExtensionModal cb={callback}/>}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ModalBase;