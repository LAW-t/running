import { baseRequest } from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/biz/like/` + url, ...arg)

/**
 * BUS_LIKEApi接口管理器
 *
 * @author twh
 * @date  2023/03/02 14:48
 **/
export default {
	// 获取BUS_LIKE分页
	busLikePage(data) {
		return request('page', data, 'get')
	},
	// 获取BUS_LIKE列表
	busLikeList(data) {
		return request('list', data, 'get')
	},
	// 提交BUS_LIKE表单 edit为true时为编辑，默认为新增
	busLikeSubmitForm(data, edit = false) {
		return request(edit ? 'add' : 'edit', data)
	},
	// 删除BUS_LIKE
	busLikeDelete(data) {
		return request('delete', data)
	},
	// 获取BUS_LIKE详情
	busLikeDetail(data) {
		return request('detail', data, 'get')
	}
}
