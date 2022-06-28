import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import CreateCsrPage from './pages/CreateCsrPage';
import Register from './pages/Register';
import CertificateReviewPage from './pages/CertificateReviewPage';
import IssueCertificatePage from './pages/IssueCertificatePage';
import ViewUsersPage from './pages/ViewUsersPage';
import ViewCsrs from './pages/ViewCsrs';
import RevokeCert from './pages/RevokeCert';
import UnblockUserPage from './pages/UnblockUserPage';
import ConfirmCsr from './pages/ConfirmCsr';

function App() {
  return (
    <div className='App'>
      <Router>
        <Routes>
          <Route exact path="/" element={<Login />}/>
          <Route exact path="/register" element={<Register />}/>
          <Route exact path="/create-csr" element={<CreateCsrPage />}/>
          <Route exact path="/certificate" element={<CertificateReviewPage />}/>
          <Route exact path="/issue-cert/:csrId" element={<IssueCertificatePage />}/>
          <Route exact path="/users" element={<ViewUsersPage />}/>
          <Route exact path="/user/:userId" element={<UnblockUserPage />}/>
          <Route exact path="/csrs" element={<ViewCsrs />}/>
          <Route exact path="/cert-details/:alias" element={<RevokeCert />}/>
          <Route exact path="/csr-confirm/:csrId" element={<ConfirmCsr />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
