<template>
    <a-card :bordered="false">
        <s-table
            ref="table"
            :columns="columns"
            :data="loadData"
            :alert="options.alert.show"
            bordered
            :row-key="(record) => record.postId"
            :tool-config="toolConfig"
            :row-selection="options.rowSelection"
        >
            <template #operator class="table-operator">
                <a-space>
                    <a-button type="primary" @click="formRef.onOpen()" v-if="hasPerm('busLikeAdd')">
                        <template #icon><plus-outlined /></template>
                        新增
                    </a-button>
                    <a-button danger @click="deleteBatchBusLike()" v-if="hasPerm('busLikeBatchDelete')">删除</a-button>
                </a-space>
            </template>
            <template #bodyCell="{ column, record }">
                <template v-if="column.dataIndex === 'action'">
                    <a-space>
                        <a @click="formRef.onOpen(record)" v-if="hasPerm('busLikeEdit')">编辑</a>
                        <a-divider type="vertical" v-if="hasPerm(['busLikeEdit', 'busLikeDelete'], 'and')" />
                        <a-popconfirm title="确定要删除吗？" @confirm="deleteBusLike(record)">
                            <a-button type="link" danger size="small" v-if="hasPerm('busLikeDelete')">删除</a-button>
                        </a-popconfirm>
                    </a-space>
                </template>
            </template>
        </s-table>
    </a-card>
    <Form ref="formRef" @successful="table.refresh(true)" />
</template>

<script setup name="like">
    import { message } from 'ant-design-vue'
    import Form from './form.vue'
    import busLikeApi from '@/api/biz/busLikeApi'
    const table = ref()
    const formRef = ref()
    const toolConfig = { refresh: true, height: true, columnSetting: true, striped: false }
    const columns = [
    ]
    // 操作栏通过权限判断是否显示
    if (hasPerm(['busLikeEdit', 'busLikeDelete'])) {
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
        return busLikeApi.busLikePage(parameter).then((data) => {
            return data
        })
    }
    // 删除
    const deleteBusLike = (record) => {
        let params = [
            {
                postId: record.postId
            }
        ]
        busLikeApi.busLikeDelete(params).then(() => {
            table.value.refresh(true)
        })
    }
    // 批量删除
    const deleteBatchBusLike = () => {
        if (selectedRowKeys.value.length < 1) {
            message.warning('请选择一条或多条数据')
            return false
        }
        const params = selectedRowKeys.value.map((m) => {
            return {
                postId: m
            }
        })
        busLikeApi.busLikeDelete(params).then(() => {
            table.value.clearRefreshSelected()
        })
    }
</script>
