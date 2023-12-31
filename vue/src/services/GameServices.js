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
    getAllGenres() {
        return axios.get('/search/genre');
    },
    getAllDevelopers() {
        return axios.get('/search/devs');
    },
    getAllPublishers(){
        return axios.get('/search/pubs')
    },
    getReviewByGameId(gameId) {
        return axios.get(`/reviews/game/${gameId}`);
    },
    deleteReview(reviewId) {
        return axios.delete(`/reviews/${reviewId}`);
    },
    getReviewById(reviewId) {
        return axios.get(`/reviews/${reviewId}`);
    },
    addReview(review){
        return axios.post('/reviews/',review);
    },
    updateReview(reviewId){
        return axios.put(`/reviews/${reviewId}`);
    },
    getListsByLists(userId) {
        return axios.get(`/lists/user/${userId}`);
    },
    getReviewByUserId(userId){
        return axios.get(`/reviews/user/${userId}`);
    },
    addGameToLists(list){
        return axios.post('/lists/game', list);
    },
    
    deleteGameFromLists(list){
        return axios.put('/lists/game', list);
    },
    getListOfGames(listId) {
        return axios.get(`/games/list/${listId}`);
    }
}