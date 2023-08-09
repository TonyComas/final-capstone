import axios from 'axios';

export default {

    getAllGames() {
        return axios.get('/games');
    },

    getGameById(gameId) {
        return axios.get(`/games/${gameId}`);
    },
    addGame(game) {
        return axios.post(`/games/`,game);
    },
    deleteGame(gameId) {
        return axios.delete(`/games/${gameId}`);
    },
    updateGame(gameId, game) {
        return axios.put(`/games/${gameId}`,game);
    },
    
    

}