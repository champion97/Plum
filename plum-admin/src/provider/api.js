import request from "../config/axios";

var api = {}

api.login = (data) => {
  return request.post('/account/login', data)
}

api.getArticlesByPage = (pageNum, pageSize, keyword) => {
  return request.get(`/admin/articles?pageNum=${pageNum}&pageSize=${pageSize}&keyword=${keyword || ''}`)
}

api.getArticle = (aid)=>{
  return request.get(`/admin/articles/${aid}`)
}

api.addArticle = (data)=>{
  return request.post(`/admin/articles`, data)
}

api.modifyArticle = (data)=>{
  return request.put(`/admin/articles`, data)
}

api.modifyArticleStatus = (id, status)=>{
  return request.put(`/admin/articles/${id}/${status}`)
}


api.removeArticle = (aid)=>{
  return request.delete(`/admin/articles/${aid}`)
}

api.getCategories = ()=>{
  return request.get(`/admin/categories`)
}

api.addCategory = (data)=>{
  return request.post(`/admin/categories`, data)
}

api.modifyCategory = (data)=>{
  return request.put(`/admin/categories`, data)
}

api.removeCategory = (cid)=>{
  return request.delete(`/admin/categories/${cid}`)
}

api.getTags = ()=>{
  return request.get(`/admin/tags`)
}

api.addTag = (data)=>{
  return request.post(`/admin/tags`, data)
}

api.modifyTag = (data)=>{
  return request.put(`/admin/tags`, data)
}

api.removeTag = (tid)=>{
  return request.delete(`/admin/tags/${tid}`)
}

api.auditComment = (cid)=>{
  return request.put(`/admin/comments/audit/${cid}`)
}

api.getCommentsByPage = (pageNum, pageSize, audited)=>{
  return request.get(`/admin/comments?pageNum=${pageNum}&pageSize=${pageSize}&audited=${audited||''}`)
}

api.removeComment = (cid)=>{
  return request.delete(`/admin/comments/${cid}`)
}

export default api
