import { baseRequest } from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/biz/post/` + url, ...arg)

/**
 * BUS_POSTApi接口管理器
 *
 * @author twh
 * @date  2023/03/02 14:45
 **/
export default {
	// 获取BUS_POST分页
	busPostPage(data) {
		return request('page', data, 'get')
	},
	// 获取BUS_POST列表
	busPostList(data) {
		return request('list', data, 'get')
	},
	// 提交BUS_POST表单 edit为true时为编辑，默认为新增
	busPostSubmitForm(data, edit = false) {
		return request(edit ? 'add' : 'edit', data)
	},
	// 删除BUS_POST
	busPostDelete(data) {
		return request('delete', data)
	},
	// 获取BUS_POST详情
	busPostDetail(data) {
		return request('detail', data, 'get')
	}
}
