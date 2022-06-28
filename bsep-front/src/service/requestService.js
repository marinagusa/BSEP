import axios from "axios";

const API = "https://localhost:8080";

const request = axios.create({withCredentials: true});

request.interceptors.response.use(
    (response) => {
        return response;
    }, (error) => {
        if(error.response.status === 401){
            window.location.replace("/");
        }
    });

function addInterceptors(){
    request.interceptors.request.use((config) => {
        let jwt = sessionStorage.getItem('jwt');

        if(jwt && new Date(sessionStorage.getItem('validUntil')) > Date.now()){
            config.headers.authorization = `Bearer ${jwt}`;
        }
        return config;
    });
}

export function login(dto, callback, errorCallback){
    request
    .post(`${API}/auth/login`, dto)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function register(dto, callback, errorCallback){
    request
    .post(`${API}/auth/register`, dto)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function addCsr(dto, callback, errorCallback){
    addInterceptors();
    request
    .post(`${API}/csr/`, dto)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function getCsrs(callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/csr/`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function deleteCsr(id, callback, errorCallback){
    addInterceptors();
    request
    .delete(`${API}/csr/${id}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function findCsrById(id, callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/csr/${id}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function findAllCertificates(callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/certificates/all`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function issueCertificate(dto, callback, errorCallback){
    addInterceptors();
    request
    .post(`${API}/certificates/`, dto)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function getAliases(callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/certificates/all`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function getCertInfo(alias, callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/certificates/one/${alias}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    })
}

export function revokeCertificate(alias, reason, callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/certificates/revoke/${alias}/${reason}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function addUser(dto, callback, errorCallback){
    addInterceptors();
    request
    .post(`${API}/users/`, dto)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function getAllUsers(callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/users/`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function findUserById(id, callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/users/${id}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function deleteUser(id, callback, errorCallback){
    addInterceptors();
    request
    .delete(`${API}/users/${id}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function changeUserRole(userId, roleId, callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/users/change-role/${userId}/${roleId}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function searchFilterUsers(dto, callback, errorCallback){
    addInterceptors();
    request
    .post(`${API}/users/search-filter`, dto)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function enableUser(id, callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/users/enable/${id}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function getRevoked(callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/certificates/get-revoked`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function logout(callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/auth/logout`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}

export function confirmCsr(csrId, callback, errorCallback){
    addInterceptors();
    request
    .get(`${API}/csr/confirm/${csrId}`)
    .then(response => {
        callback(response);
    })
    .catch(error => {
        errorCallback(error);
    });
}