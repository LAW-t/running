<template>
    <a-card :bordered="false">
        <a-form ref="searchFormRef" name="advanced_search" :model="searchFormState" class="ant-advanced-search-form mb-4">
            <a-row :gutter="24">
                <a-col :span="6">
                    <a-form-item label="话题标题" name="name">
                        <a-input v-model:value="searchFormState.name" placeholder="请输入话题标题" />
                    </a-form-item>
                </a-col>
                <a-col :span="6">
                    <a-button type="primary" @click="table.refresh(true)">查询</a-button>
                    <a-button style="margin: 0 8px" @click="() => searchFormRef.resetFields()">重置</a-button>
                </a-col>
            </a-row>
        </a-form>
        <s-table
            ref="table"
            :columns="columns"
            :data="loadData"
            :alert="options.alert.show"
            bordered
            :row-key="(record) => record.id"
            :tool-config="toolConfig"
            :row-selection="options.rowSelection"
        >
            <template #operator class="table-operator">
                <a-space>
                    <a-button type="primary" @click="formRef.onOpen()" v-if="hasPerm('busTopicAdd')">
                        <template #icon><plus-outlined /></template>
                        新增
                    </a-button>
                    <a-button danger @click="deleteBatchBusTopic()" v-if="hasPerm('busTopicBatchDelete')">删除</a-button>
                </a-space>
            </template>
            <template #bodyCell="{ column, record }">
                <template v-if="column.dataIndex === 'action'">
                    <a-space>
                        <a @click="formRef.onOpen(record)" v-if="hasPerm('busTopicEdit')">编辑</a>
                        <a-divider type="vertical" v-if="hasPerm(['busTopicEdit', 'busTopicDelete'], 'and')" />
                        <a-popconfirm title="确定要删除吗？" @confirm="deleteBusTopic(record)">
                            <a-button type="link" danger size="small" v-if="hasPerm('busTopicDelete')">删除</a-button>
                        </a-popconfirm>
                    </a-space>
                </template>
            </template>
        </s-table>
    </a-card>
    <Form ref="formRef" @successful="table.refresh(true)" />
</template>

<script setup name="topic">
    import { message } from 'ant-design-vue'
    import Form from './form.vue'
    import busTopicApi from '@/api/biz/busTopicApi'
    let searchFormState = reactive({})
    const searchFormRef = ref()
    const table = ref()
    const formRef = ref()
    const toolConfig = { refresh: true, height: true, columnSetting: true, striped: false }
    const columns = [
        {
            title: '话题标题',
            dataIndex: 'name'
        },
        {
            title: '介绍',
            dataIndex: 'introduce'
        },
        {
            title: '背景图片',
            dataIndex: 'image'
        },
        {
            title: '评论数',
            dataIndex: 'totalComments'
        },
        {
            title: '浏览量',
            dataIndex: 'totalView'
        },
    ]
    // 操作栏通过权限判断是否显示
    if (hasPerm(['busTopicEdit', 'busTopicDelete'])) {
        columns.push({
            title: '操作',
            dataIndex: 'action',
            align: 'center',
            width: '150px'
        })
    }
    let selectedRowKeys = ref([])
    // 列表选择配置
    const options = {
        alert: {
            show: false,
            clear: () => {
                selectedRowKeys = ref([])
            }
        },
        rowSelection: {
            onChange: (selectedRowKey, selectedRows) => {
                selectedRowKeys.value = selectedRowKey
            }
        }
    }
    const loadData = (parameter) => {
        const searchFormParam = JSON.parse(JSON.stringify(searchFormState))
        return busTopicApi.busTopicPage(Object.assign(parameter, searchFormParam)).then((data) => {
            return data
        })
    }
    // 删除
    const deleteBusTopic = (record) => {
        let params = [
            {
                id: record.id
            }
        ]
        busTopicApi.busTopicDelete(params).then(() => {
            table.value.refresh(true)
        })
    }
    // 批量删除
    const deleteBatchBusTopic = () => {
        if (selectedRowKeys.value.length < 1) {
            message.warning('请选择一条或多条数据')
            return false
        }
        const params = selectedRowKeys.value.map((m) => {
            return {
                id: m
            }
        })
        busTopicApi.busTopicDelete(params).then(() => {
            table.value.clearRefreshSelected()
        })
    }
</script>
