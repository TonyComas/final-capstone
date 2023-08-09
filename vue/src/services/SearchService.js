import axios from 'axios';

export default {

    getAllGenres() {
        return axios.get('/search/genre');
    },
    getAllDevelopers() {
        return axios.get('/search/devs');
    },
    getAllPublishers() {
        return axios.get('/search/pubs');
    }
    

}