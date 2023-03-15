<template>
    <a-drawer
        :title="formData.id ? '编辑BUS_TOPIC' : '增加BUS_TOPIC'"
        :width="600"
        :visible="visible"
        :destroy-on-close="true"
        :footer-style="{ textAlign: 'right' }"
        @close="onClose"
    >
        <a-form ref="formRef" :model="formData" :rules="formRules" layout="vertical">
            <a-form-item label="话题标题：" name="name">
                <a-input v-model:value="formData.name" placeholder="请输入话题标题" allow-clear />
            </a-form-item>
            <a-form-item label="介绍：" name="introduce">
                <a-textarea v-model:value="formData.introduce" placeholder="请输入介绍" :auto-size="{ minRows: 3, maxRows: 5 }" />
            </a-form-item>
            <a-form-item label="背景图片：" name="image">
                <a-input v-model:value="formData.image" placeholder="请输入背景图片" allow-clear />
            </a-form-item>
        </a-form>
        <template #footer>
            <a-button style="margin-right: 8px" @click="onClose">关闭</a-button>
            <a-button type="primary" @click="onSubmit" :loading="submitLoading">保存</a-button>
        </template>
    </a-drawer>
</template>

<script setup name="busTopicForm">
    import { cloneDeep } from 'lodash-es'
    import { required } from '@/utils/formRules'
    import busTopicApi from '@/api/biz/busTopicApi'
    // 抽屉状态
    const visible = ref(false)
    const emit = defineEmits({ successful: null })
    const formRef = ref()
    // 表单数据
    const formData = ref({})
    const submitLoading = ref(false)

    // 打开抽屉
    const onOpen = (record) => {
        visible.value = true
        if (record) {
            let recordData = cloneDeep(record)
            formData.value = Object.assign({}, recordData)
        }
    }
    // 关闭抽屉
    const onClose = () => {
        formRef.value.resetFields()
        formData.value = {}
        visible.value = false
    }
    // 默认要校验的
    const formRules = {
    }
    // 验证并提交数据
    const onSubmit = () => {
        formRef.value
            .validate()
            .then(() => {
                submitLoading.value = true
                const formDataParam = cloneDeep(formData.value)
                busTopicApi
                    .busTopicSubmitForm(formDataParam, !formDataParam.id)
                    .then(() => {
                        onClose()
                        emit('successful')
                    })
                    .finally(() => {
                        submitLoading.value = false
                    })
            })
    }
    // 抛出函数
    defineExpose({
        onOpen
    })
</script>
