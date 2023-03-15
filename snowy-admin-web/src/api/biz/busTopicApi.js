import { baseRequest } from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/biz/topic/` + url, ...arg)

/**
 * BUS_TOPICApi接口管理器
 *
 * @author twh
 * @date  2023/03/02 14:35
 **/
export default {
	// 获取BUS_TOPIC分页
	busTopicPage(data) {
		return request('page', data, 'get')
	},
	// 获取BUS_TOPIC列表
	busTopicList(data) {
		return request('list', data, 'get')
	},
	// 提交BUS_TOPIC表单 edit为true时为编辑，默认为新增
	busTopicSubmitForm(data, edit = false) {
		return request(edit ? 'add' : 'edit', data)
	},
	// 删除BUS_TOPIC
	busTopicDelete(data) {
		return request('delete', data)
	},
	// 获取BUS_TOPIC详情
	busTopicDetail(data) {
		return request('detail', data, 'get')
	}
}
