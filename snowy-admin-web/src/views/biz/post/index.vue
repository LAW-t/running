<template>
    <a-card :bordered="false">
        <a-form ref="searchFormRef" name="advanced_search" :model="searchFormState" class="ant-advanced-search-form mb-4">
            <a-row :gutter="24">
                <a-col :span="6">
                    <a-form-item label="昵称" name="nickName">
                        <a-input v-model:value="searchFormState.nickName" placeholder="请输入昵称" />
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
                    <a-button type="primary" @click="formRef.onOpen()" v-if="hasPerm('busPostAdd')">
                        <template #icon><plus-outlined /></template>
                        新增
                    </a-button>
                    <a-button danger @click="deleteBatchBusPost()" v-if="hasPerm('busPostBatchDelete')">删除</a-button>
                </a-space>
            </template>
            <template #bodyCell="{ column, record }">
                <template v-if="column.dataIndex === 'action'">
                    <a-space>
                        <a @click="formRef.onOpen(record)" v-if="hasPerm('busPostEdit')">编辑</a>
                        <a-divider type="vertical" v-if="hasPerm(['busPostEdit', 'busPostDelete'], 'and')" />
                        <a-popconfirm title="确定要删除吗？" @confirm="deleteBusPost(record)">
                            <a-button type="link" danger size="small" v-if="hasPerm('busPostDelete')">删除</a-button>
                        </a-popconfirm>
                    </a-space>
                </template>
            </template>
        </s-table>
    </a-card>
    <Form ref="formRef" @successful="table.refresh(true)" />
</template>

<script setup name="post">
    import { message } from 'ant-design-vue'
    import Form from './form.vue'
    import busPostApi from '@/api/biz/busPostApi'
    let searchFormState = reactive({})
    const searchFormRef = ref()
    const table = ref()
    const formRef = ref()
    const toolConfig = { refresh: true, height: true, columnSetting: true, striped: false }
    const columns = [
        {
            title: '内容',
            dataIndex: 'content'
        },
        {
            title: '点赞数',
            dataIndex: 'totalLike'
        },
        {
            title: '评论数',
            dataIndex: 'totalComments'
        },
        {
            title: '昵称',
            dataIndex: 'nickName'
        },
        {
            title: '标题',
            dataIndex: 'title'
        },
        {
            title: '浏览数',
            dataIndex: 'totalView'
        },
    ]
    // 操作栏通过权限判断是否显示
    if (hasPerm(['busPostEdit', 'busPostDelete'])) {
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
        return busPostApi.busPostPage(Object.assign(parameter, searchFormParam)).then((data) => {
            return data
        })
    }
    // 删除
    const deleteBusPost = (record) => {
        let params = [
            {
                id: record.id
            }
        ]
        busPostApi.busPostDelete(params).then(() => {
            table.value.refresh(true)
        })
    }
    // 批量删除
    const deleteBatchBusPost = () => {
        if (selectedRowKeys.value.length < 1) {
            message.warning('请选择一条或多条数据')
            return false
        }
        const params = selectedRowKeys.value.map((m) => {
            return {
                id: m
            }
        })
        busPostApi.busPostDelete(params).then(() => {
            table.value.clearRefreshSelected()
        })
    }
</script>
