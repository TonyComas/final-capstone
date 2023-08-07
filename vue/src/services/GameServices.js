import axios from 'axios';

export default {

    getAllGames() {
        return axios.get('/games');
    },

    getGameById(gameId) {
        return axios.get(`/games/${gameId}`);
    }

}