import { baseRequest } from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/biz/comments/` + url, ...arg)

/**
 * BUS_COMMENTSApi接口管理器
 *
 * @author twh
 * @date  2023/03/02 14:55
 **/
export default {
	// 获取BUS_COMMENTS分页
	busCommentsPage(data) {
		return request('page', data, 'get')
	},
	// 获取BUS_COMMENTS列表
	busCommentsList(data) {
		return request('list', data, 'get')
	},
	// 提交BUS_COMMENTS表单 edit为true时为编辑，默认为新增
	busCommentsSubmitForm(data, edit = false) {
		return request(edit ? 'add' : 'edit', data)
	},
	// 删除BUS_COMMENTS
	busCommentsDelete(data) {
		return request('delete', data)
	},
	// 获取BUS_COMMENTS详情
	busCommentsDetail(data) {
		return request('detail', data, 'get')
	}
}
