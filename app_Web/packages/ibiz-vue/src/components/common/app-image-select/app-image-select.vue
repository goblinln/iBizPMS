<template>
    <div :class="{ ['image-selector']: true, ['has-picture']: fileList.length > 0, ['disabled']: disabled }">
        <el-upload
            action="#"
            ref="imgupload"
            :disabled="disabled"
            list-type="picture-card"
            :on-preview="() => this.handlePictureCardPreview()"
            :auto-upload="false"
            :on-change="uploading"
            :multiple="multiple"
            :limit="1"
            :file-list="fileList"
        >
            <div slot="file" class="image-wrapper" slot-scope="{ file }">
                <div v-if="!svg" class="image-img">
                    <img class="el-upload-list__item-thumbnail" :src="dialogImageUrl" alt="" />
                </div>
                <div v-else v-html="svg" class="image-svg" />
                <span class="el-upload-list__item-actions">
                    <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                        <i class="el-icon-zoom-in"></i>
                    </span>
                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="removeFile(file)">
                        <i class="el-icon-delete"></i>
                    </span>
                </span>
            </div>
            <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible" :modal-append-to-body="false">
            <div v-if="!svg" class="image-img">
                <img :src="dialogImageUrl" alt="" />
            </div>
            <div v-else v-html="svg" class="image-svg" />
        </el-dialog>
    </div>
</template>

<script lang="ts">
import { component } from 'node_modules/vue/types/umd';
import { Vue, Watch, Component, Prop, Model, Emit } from 'vue-property-decorator';

@Component({})
export default class AppImageSelect extends Vue {
    /**
     * 编辑器值
     * @type {any}
     * @memberof AppImageSelect
     */
    @Model('change') readonly value?: any;

    /**
     * 表单项名称
     * @type {String}
     * @memberof AppImageSelect
     */
    @Prop() public name?: string;

    /**
     * 是否多选
     * @type {boolean}
     * @memberof AppImageSelect
     */
    @Prop() public multiple?: boolean;
    
    /**
     * 是否禁用
     * @type {boolean}
     * @memberof AppImageSelect
     */
    @Prop() public disabled?: boolean;

    
    /**
     * 监听值变化
     * 
     * @memberof AppImageSelect
     */
    @Watch('value')
    onValueChange(newVal: any, oldVal: any) {
        if (newVal) {
            const reg = /^(data:image)/;
            if (reg.test(newVal)) {
                this.dialogImageUrl = newVal;
                this.svg = '';
            } else {
                this.svg = newVal;
            }
            this.fileList.push({ url: newVal });
        }
    }

    /**
     * 图片文件列表
     * @type {*}
     * @memberof AppImageSelect
     */
    fileList: any = [];

    /**
     * 对话框绑定值
     * @type {boolean}
     * @memberof AppImageSelect
     */
    dialogVisible: boolean = false;

    /**
     * img图片地址
     * @type {*}
     * @memberof AppImageSelect
     */
    dialogImageUrl: any = '';

    /**
     * svg图片内容
     * @type {string}
     * @memberof AppImageSelect
     */
    svg:string = '';

    /**
     * 打开图片预览
     * 
     * @memberof AppImageSelect
     */
    handlePictureCardPreview() {
        this.dialogVisible = true;
    }

    /**
     * 图片上传
     * 
     * @memberof AppImageSelect
     */
    uploading(file: any, fileList: any) {
        const reg = /(.png|.jpg|.jpeg|.svg)$/;
        if (reg.test(file.name)) {
            const svg = /.svg$/;
            const reader = new FileReader();
            if (svg.test(file.name)) {
                reader.readAsText(file.raw); //读取文件
                reader.onload = (evt: any) => {
                    //读取完文件之后会回来这里
                    const fileString = evt.target.result; // 读取文件内容
                    this.emitChangeEvent({
                        name: this.name,
                        value: fileString,
                    });
                };
            } else {
                reader.readAsDataURL(file.raw);
                reader.onload = (evt: any) => {
                    const fileString: string = evt.target.result;
                    this.emitChangeEvent({
                        name: this.name,
                        value: fileString,
                    });
                };
            }
        } else {
            this.$throw(this.$t('components.appfileupload.filetypeerrortitle'))
        }
    }

    /**
     * 图片删除
     * 
     * @memberof AppImageSelect
     */
    removeFile() {
        this.fileList.splice(0, 1);
        this.emitChangeEvent({
            name: this.name,
            value: null,
        });
    }

    /**
     * 触发change事件
     *
     * @param {*} data
     * @memberof AppImageSelect
     */
    emitChangeEvent(data: any) {
        this.$emit('formitemvaluechange', data);
    }
}
</script>

<style lang="less">
@import './app-image-select.less';
</style>
