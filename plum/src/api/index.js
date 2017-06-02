import axios from 'axios'

var request = axios.create({
  baseURL: 'http://127.0.0.1:8080/api'
});

request.defaults.headers.post['Content-Type'] = 'application/json'

var api = {}

api.getArticles = (pageNum, pageSize) => {
  return request.get(`/articles?page=${pageNum}&size=${pageSize}`)
}

api.getArticle = (id) => {
  return request.get(`/articles/${id}`)
}

api.getComments = (aid) => {
  return request.get(`/comments/${aid}`)
}

api.comment = (data) => {
  return request.post(`/comments`, data)
}

api.getSinglePage = (slug) => {
  return request.get(`/page/${slug}`)
}
