<template>
    <div class="rich-text-editor">
        <div class="editor-custom-action">
            <Poptip ref="propip" trigger="hover" placement="top-end" :title="$t('app.components.richtext.title')" :width="250" @on-popper-show="openPoper()">
                <Button class="appTemplate">
                    {{$t('app.components.richtext.save')}}
                </Button>
                <template slot="content">
                    <div>
                        <Input v-model="templateTitle" :placeholder="$t('app.components.richtext.title')" />
                        <div style="margin-top: 5px;">
                            <Checkbox v-model="single">{{$t('app.components.richtext.pub')}}</Checkbox>
                            <Button @click="saveTemplate()">{{ $t('app.commonWords.save') }}</Button>&nbsp;
                            <Button @click="onCancel()">{{ $t('app.commonWords.cancel') }}</Button>
                        </div>
                    </div>
                </template>
            </Poptip>
            <Dropdown trigger="click">
                <Button class="appTemplate">
                    {{$t('app.components.richtext.apply')}}
                    <Icon type="ios-arrow-down"></Icon>
                </Button>
                <DropdownMenu slot="list" >
                    <Tabs v-model="ibizpublic" :animated="false">
                        <TabPane :label="$t('app.components.richtext.pri')" name="0"></TabPane>
                        <TabPane :label="$t('app.components.richtext.pub')" name="1"></TabPane>
                    </Tabs>
                    <DropdownItem v-for="(item,index) in appTemplate" :key='index' :value="item.content">
                        <div class="keyOperation" v-if="item.ibizpublic == ibizpublic">
                            <span class="span-content" @click="appTemplateFill(item.content)">
                                {{item.title+"-"+item.account}}
                            </span> 
                            <span class="icon-close" @click="removeAppTemplate(item)">
                                <Icon type="md-close" size="18"/>
                            </span>
                        </div>
                    </DropdownItem>
                </DropdownMenu>
            </Dropdown>
        </div>
        <div :class="editorClass">
            <textarea :id="id"></textarea>
            <div :style="[personPosi,{visibility: showSelect,position: 'absolute',width: '120px'}]">
                <Input search size="small" class="person-search" v-model="personSearchText" @on-change="personSearch" :element-id="'person-search'+id" style="padding-bottom: 6px;"/>
                <div class="select-person">
                    <ul class="person-wrap">
                        <li :class="['row',item.selectState ? 'selectRow': '']" v-for="(item,index) in items" :key="index" @click="selectPerson(item)" @mouseover="onMousevoer(index)">
                            {{item.text}}
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>
<script lang = 'ts'>
import { Vue, Component, Prop, Model, Watch } from 'vue-property-decorator';
import { Subject } from 'rxjs';
import { Environment } from '@/environments/environment';
import axios from 'axios';
import { ImgurlBase64, LogUtil } from 'ibiz-core';
import tinymce from "tinymce/tinymce";
// import 'tinymce/themes/modern';
import 'tinymce/themes/silver';
import 'tinymce/plugins/link';
import 'tinymce/plugins/paste';
import 'tinymce/plugins/table';
import 'tinymce/plugins/image';
import 'tinymce/plugins/imagetools';
import 'tinymce/plugins/codesample';
import 'tinymce/plugins/code';
import 'tinymce/plugins/fullscreen';
import 'tinymce/plugins/preview';
import 'tinymce/plugins/quickbars';
import 'tinymce/icons/default/icons.min.js';
import { CodeListService, UserTplService } from 'ibiz-service';

const tinymceCode:any = tinymce;

// import { UserTplService } from 'ibiz-service';

@Component({})
export default class RichTextEditor extends Vue {

    /**
     * 人员列表是否显示
     * 
     * @type {string}
     * @memberof RichTextEditor
     */
    public showSelect: string = 'hidden';

    /**
     * 人员列表数据集
     * 
     * @type {Array<any>}
     * @memberof RichTextEditor
     */
    public items: Array<any> = [];

    /**
     * 人员搜索值
     * 
     * @type {string}
     * @memberof RichTextEditor
     */
    public personSearchText: string = '';

    /**
     * 初始化人员列表
     * 
     * @type {Array<any>}
     * @memberof RichTextEditor
     */
    public personList:Array<any> = [];

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppCheckBox
     */  
    public codeListService:CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 人员列表显示位置
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    public personPosi: any = {};

    /**
     * 富文本编辑区域光标信息
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    public lastSelection: any = {};

    /**
     * "@"符号计数器
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    public atNumber: number = 0;

    /**
     * 上下键选择计数器
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    public personNumber: number = -1;

    /**
     * 传入值
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    @Prop() value?: any;
    
    /**
     * 输入name
     * 
     * @type {string}
     * @memberof RichTextEditor
     */
    @Prop() name?: string;

    /**
     * 输入高度
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    @Prop() height?: any;

    /**
     * 是否禁用
     * 
     * @type {boolean}
     * @memberof RichTextEditor
     */
    @Prop() disabled?: boolean;

    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof RichTextEditor
     */
    @Prop() public formState?: Subject<any>;

    /**
     * 上传文件路径
     * 
     * @type {string}
     * @memberof RichTextEditor
     */
    public uploadUrl = Environment.UploadFile;

    /**
     * 下载路径
     * 
     * @type {string}
     * @memberof RichTextEditor
     */
    public downloadUrl = Environment.ExportFile;

    /**
     * 当前富文本
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    public editor: any = null;

    /**
     *  当前富文本id
     * 
     * @type {string}
     * @memberof RichTextEditor
     */
    public id: string = this.$util.createUUID();

    /**
     * 当前语言,默认中文
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    public langu: any = localStorage.getItem('local') ? localStorage.getItem('local') : 'zh-CN' ;

    /**
     * 上传params
     *
     * @type {Array<any>}
     * @memberof RichTextEditor
     */
    public upload_params: Array<any> = [];

    /**
     * 导出params
     *
     * @type {Array<any>}
     * @memberof RichTextEditor
     */
    public export_params: Array<any> = [];

    /**
     * 上传参数
     *
     * @type {string}
     * @memberof RichTextEditor
     */
    @Prop() public uploadparams?: any;

    /**
     * 下载参数
     *
     * @type {string}
     * @memberof RichTextEditor
     */
    @Prop() public exportparams?: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof RichTextEditor
     */
    @Prop() public viewparams!: any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof RichTextEditor
     */
    @Prop() public context!: any;

    /**
     * 表单数据
     *
     * @type {string}
     * @memberof RichTextEditor
     */
    @Prop() public data!: string;

    /**
     * 应用模板参数
     * 
     * @type {string}
     * @memberof RichTextEditor
     */
    @Prop() public localParam: any;

    /**
     * 注入实体服务
     */
    public userTplService: UserTplService = new UserTplService();

    /**
     * 应用模板集合
     */
    public appTemplate: Array<any>=[];

    /**
     * 模板标题
     */
    public templateTitle: string = '';

    /**
     * 语言映射文件
     * 
     * @type {*}
     * @memberof RichTextEditor
     */
    public languMap:any = {
        'zh-CN': 'zh_CN',
        'en-US': 'en_US',
    };

    /**
     * 是否处于激活状态
     * 
     * @type {boolean}
     * @memberof RichTextEditor
     */
    public isActived: boolean = true;

    /**
     * 是否需要初始化
     * 
     * @type {boolean}
     * @memberof RichTextEditor
     */
    public isNeedInit: boolean = false;

    /**
     * 多选框默认值
     * @type {boolean}
     * @memberof RichTextEditor
     */
    public single: boolean = false;

    /**
     * 模板类型（默认所有类型）
     * @type {string}
     * @memberof RichTextEditor
     */
    public ibizpublic: string = '0';

    /**
     * 上传的图片id与类型集合
     * @type {string}
     * @memberof RichTextEditor
     */
    public imgsrc: Array<any> = [];

    /**
     * 编辑器样式类
     * @type {string}
     * @memberof RichTextEditor
     */
    public editorClass: string = 'app-rich-text-editor';

    /**
     * 生命周期
     *
     * @memberof RichTextEditor
     */
    public created() {
        this.appTemplateData();
        if(this.formState) {
            this.formState.subscribe(({ type, data }) => {
                if (Object.is('load', type)) {
                    this.getParams();
                    if (!this.value) {
                        this.init();
                    }
                }
            });
        }
        //点击富文本外部区域时关闭列表(除点击搜索框)
        window.addEventListener("click", this.onClick);
        //点击搜索框后提供的按键功能
        window.addEventListener("keydown", this.keyDown);
    }
    
    /**
     * 生命周期：激活
     *
     * @memberof RichTextEditor
     */
    public activated(){
        this.isActived = true;
        if(this.isNeedInit){
            this.init();
            this.isNeedInit = false;
        }
    }

    /**
     * 生命周期：缓存
     *
     * @memberof RichTextEditor
     */
    public deactivated(){
        this.isActived = false;
    }

    /**
     * 生命周期：初始化富文本
     * 
     * @memberof RichTextEditor
     */
    public mounted() {
        this.init();
        this.readyUserItems();
        const ele: any = this.isDrawer(this.$el);
        if (ele) {
            ele.style.transform.replace(/translateX\(-?(\d+)%?(px)?\)\s/i,(match: string, p1: string) => {
              this.editorClass = this.editorClass + '-' + parseInt(p1);
            });
        }
    }
    
    /**
     * 是否抽屉打开
     * 
     * @memberof RichTextEditor
     */
    public isDrawer(ele: any): any {
        let pele: any = ele.parentNode;
        if(!pele) {
            return false;
        }
        if(pele.className && pele.className.indexOf('studio-drawer-content') >= 0) {
            return pele;
        }
        return this.isDrawer(pele);
        
    }
    
    /**
     * 生命周期：销毁富文本
     * 
     * @memberof RichTextEditor
     */
    public destoryed(){
        if(this.editor){
            tinymceCode.remove('#' + this.id);
        }
    }

    /**
     * 监听value值
     * 
     * @memberof RichTextEditor
     */
    @Watch('value', { immediate: true, deep: true })
    async oncurrentContent(newval: any, val: any) {
        const content: any = this.editor ? this.editor.getContent() : undefined;
        const url = this.downloadUrl.indexOf('../') === 0 ? this.downloadUrl.substring(3) : this.downloadUrl;
        if(newval) {
            newval = newval.replace(/\{(\d+)\.(bmp|jpg|jpeg|png|tif|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw|WMF|webp)\}/g, `${url}/$1`);
            newval = await this.getImgUrlBase64(newval);
        }
        if (!Object.is(newval,content)) {
            this.init();
        }
        this.getParams();
    }

    /**
     * 获取图片Base64
     * 
     * @memberof AppRichTextEditor
     */
    public async getImgUrlBase64(html: any){
        let imgs:Array<any>|null = html.match(/<img.*?(?:>|\/>)/gi)!=null? html.match(/<img.*?(?:>|\/>)/gi):[];
        if(imgs && imgs.length>0){
             for (let item of imgs) {
                if(item.match(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig)!=null){
                    let src:any = item.match(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig)[0];
                    src = await ImgurlBase64.getInstance().getImgURLOfBase64(src.substring(5,src.length-1));
                    const image = item.replace(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig, 'src="'+src+'"');
                    html = html.replace(item, image);
                }
            }
        }
        return html;
    }

    /**
     * 更替抛到表单的图片src
     * 
     * @memberof AppRichTextEditor
     */
    public getImgUrl(html: any){
        let imgs:Array<any>|null = html.match(/<img.*?(?:>|\/>)/gi)!=null? html.match(/<img.*?(?:>|\/>)/gi):[];
        const imgsrc = this.imgsrc;
        if(imgs && imgs.length > 0 && imgsrc && imgsrc.length > 0){
            imgs.forEach((img: any, index: number) => {
                var reg = /data:image\/.*;base64,.*(?=[\'\"])/;
                if(img.match(reg) != null){
                    let base64:any = img.match(reg)[0];
                    const _imgsrc = imgsrc.find((_imgsrc: any) => Object.is(_imgsrc.key, base64));
                    if (_imgsrc) {
                        const newImg = img.replace(reg, '{'+_imgsrc.value+'}');
                        html = html.replace(img, newImg);
                    }
                }
            })
        }
        return html;
    }

    /**
     * 监听语言变化
     */
    @Watch('$i18n.locale')
    onLocaleChange(newval: any, val: any) {
        this.langu = newval;
        if(this.isActived){
            this.init();
        }else{
            this.isNeedInit = true;
        }
    }

    /**
     * 初始化富文本
     * 
     * @memberof RichTextEditor
     */
    public init() {
        this.destoryed();
        let richtexteditor = this;
        tinymceCode.init({
            selector: '#' + richtexteditor.id,
            width: 'calc( 100% - 2px )',
            height: richtexteditor.height,
            min_height: 400,
            branding: false,
            plugins: ['link', 'paste', 'table', 'image', 'codesample', 'code', 'fullscreen', 'preview', 'quickbars'],
            toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | link image | preview code fullscreen',
            contextmenu:'cut copy paste pastetext inserttable link ',
            quickbars_insert_toolbar: false,
            quickbars_selection_toolbar: 'forecolor fontsizeselect fontselect',
            codesample_languages: [
                { text: 'HTML/XML', value: 'markup' },
                { text: 'JavaScript', value: 'javascript' },
                { text: 'CSS', value: 'css' },
                { text: 'PHP', value: 'php' },
                { text: 'Ruby', value: 'ruby' },
                { text: 'Python', value: 'python' },
                { text: 'Java', value: 'java' },
                { text: 'C', value: 'c' },
                { text: 'C#', value: 'csharp' },
                { text: 'C++', value: 'cpp' }
            ],
            paste_data_images: true,
            extended_valid_elements: 'span[class|id|style|noticeusers]', 
            codesample_content_css: 'assets/tinymce/prism.css',
            skin_url: './assets/tinymce/skins/lightgray/ui/oxide',
            language_url: './assets/tinymce/langs/' + richtexteditor.languMap[richtexteditor.langu] + '.js',
            language:richtexteditor.languMap[richtexteditor.langu],
            setup: (editor: any) => {
                richtexteditor.editor = editor;
                editor.on('blur', () => {
                    let content = editor.getContent();
                    content = richtexteditor.getImgUrl(content);
                    richtexteditor.$emit('formitemvaluechange',{name: 'noticeusers',value: richtexteditor.jointSelectPersonId()});
                    richtexteditor.$emit('change', content);
                });  

                editor.on('FullscreenStateChanged',($event: any)=>{
                    let editorAction: any = richtexteditor.$el.getElementsByClassName("editor-custom-action");
                    if($event.state && editorAction){
                        editorAction[0].style.zIndex = "1300";
                        editorAction[0].style.position = "fixed";
                    }else if(editorAction){
                        editorAction[0].style.zIndex = "2";
                        editorAction[0].style.position = "absolute";
                    }
                })

                //在富文本区域鼠标点击时关闭人员列表
                editor.on('click',(e: any)=>{
                    richtexteditor.showSelect = 'hidden';
                })

                //监听键盘输入@符号和删除键
                editor.on('keydown',(event: any)=>{
                    if(!richtexteditor.personList || richtexteditor.personList.length === 0) {
                        return;
                    }
                    let selection: any = editor.selection;
                    if(event.code == "Digit2" && event.shiftKey){
                        //键入@符号时，保存富文本编辑器光标信息
                        richtexteditor.lastSelection = {
                            range: selection.getRng(),
                            selection: selection.getSel()
                        }
                        //计算人员列表框位置
                        richtexteditor.showSelectList();
                    }else if(event.keyCode === 8){
                        //删除@时删除整个@xxxx
                        let range:any = selection.getRng();
                        let removeNode:any = null;
                        if (range.startContainer.textContent.length === 1 && range.startContainer.textContent.trim() === '') {
                            removeNode = range.startContainer.previousElementSibling;
                        }
                        if (range.startContainer.parentNode.className === 'at-text') {
                            removeNode = range.startContainer.parentNode;
                        }
                        if (removeNode) {
                            richtexteditor.editor.dom.remove(removeNode.id);
                        }
                        richtexteditor.showSelect = 'hidden';
                    }else if(event.keyCode === 37 || event.keyCode === 38 || event.keyCode === 39 || event.keyCode === 40){
                        if(Object.is(richtexteditor.showSelect,'visible')){
                            //取消默认动作
                            event.preventDefault ? event.preventDefault() : event.returnValue = false;
                            //上下键进行选择
                            richtexteditor.keyboardSelect(event.keyCode);
                        }
                    }else if(event.keyCode === 13){
                        if(Object.is(richtexteditor.showSelect,'visible')){
                            //取消默认动作
                            event.preventDefault ? event.preventDefault() : event.returnValue = false;
                            //回车确认上下键所选数据
                            richtexteditor.enterSelect();
                        }
                    }else{
                        richtexteditor.showSelect = 'hidden';
                    }
                })
            },

            images_upload_handler: (bolbinfo: any, success: any, failure: any) => {
                const formData = new FormData();
                formData.append('file', bolbinfo.blob(), bolbinfo.filename());
                let _url = richtexteditor.uploadUrl;
                if (this.upload_params.length > 0 ) {
                    _url +='?';
                    this.upload_params.forEach((item:any,i:any)=>{
                        _url += `${Object.keys(item)[0]}=${Object.values(item)[0]}`;
                        if(i<this.upload_params.length-1){
                            _url += '&';
                        }
                    })    
                }
                // this.uploadUrl = _url;
                richtexteditor.uploadFile(_url, formData).subscribe((file: any) => {
                    const item: any = { id: file.fileid, type: file.ext };
                    richtexteditor.imgsrc.push(item);
                    let downloadUrl =  richtexteditor.downloadUrl;
                    if (file.filename) {
                        const id: string = file.fileid; 
                        const url: string = `${downloadUrl}/${id}`;
                        ImgurlBase64.getInstance().getImgURLOfBase64(url).then((response: any) => {
                            const imgsrc = richtexteditor.imgsrc.find((imgsrc: any) => Object.is(response,imgsrc.key));
                            if (!imgsrc) {
                                const item: any = { key: response, value: file.fileid+file.ext };
                                richtexteditor.imgsrc.push(item);
                            }
                            success(response);
                            let content = richtexteditor.editor.getContent();
                            content = richtexteditor.getImgUrl(content);
                            richtexteditor.$emit('formitemvaluechange',{name: 'noticeusers',value: richtexteditor.jointSelectPersonId()});
                            richtexteditor.$emit('change', content);
                        });
                    }
                    if (this.export_params.length > 0) {
                        downloadUrl +='?';
                        this.export_params.forEach((item:any,i:any)=>{
                            downloadUrl += `${Object.keys(item)[0]}=${Object.values(item)[0]}`;
                            if(i<this.export_params.length-1){
                                downloadUrl += '&';
                            }
                        })
                    }
                }, (error: any) => {
                    LogUtil.log(error);
                    failure('HTTP Error: ' + error.status);
                });
            },
            init_instance_callback: (editor: any) => {
                richtexteditor.editor = editor;
                const url = richtexteditor.downloadUrl.indexOf('../') === 0 ? richtexteditor.downloadUrl.substring(3) : richtexteditor.downloadUrl;
                let value = (richtexteditor.value && richtexteditor.value.length > 0) ? richtexteditor.value : '';
                value = value.replace(/\{(\d+)\.(bmp|jpg|jpeg|png|tif|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw|WMF|webp)\}/g, `${url}/$1`);
                richtexteditor.getImgUrlBase64(value).then((newValue: any) =>{
                    if (richtexteditor.editor) {
                        richtexteditor.editor.setContent(newValue);
                    }
                    if (richtexteditor.disabled) {
                        richtexteditor.editor.setMode('readonly');
                    }
                });
                //  全屏状态下鼠标移除更新值
                editor.on('mouseleave', () => {
                    let content = editor.getContent();
                    content = richtexteditor.getImgUrl(content);
                    richtexteditor.$emit('change', content);
                }); 
            }
        });
    }

    /**
     * 监听"@"符号输入后，计算人员列表框出现位置
     * 
     * @memberof RichTextEditor
     */
    public showSelectList(){
        this.showSelect = 'visible';
        this.calculate();
        this.atNumber++;
        let fakeNode: any = document.createElement('span');
        fakeNode.className = 'fake-at';
        fakeNode.innerHTML = '&nbsp';
        fakeNode.id = 'fake-at'+this.atNumber;
        this.lastSelection.range.insertNode(fakeNode);
        this.lastSelection.selection.collapse(fakeNode,1);
        //获取光标位置，编辑区域宽高，列表宽高
        const elePos: any = this.editor.dom.getPos('fake-at'+this.atNumber);
        const parent: any = this.$el.getElementsByClassName('tox-edit-area__iframe')[0];
        const parentW = parent.offsetWidth;
        const parentH = parent.offsetHeight;
        const child: any = this.$el.getElementsByClassName('select-person')[0];
        const childW = child.offsetWidth;
        const childH = child.offsetHeight;
        //默认位置
        let left:number = 30;
        let top:number = 120;
        left = elePos.x-3+left;
        top = elePos.y-41+top;
        //计算偏移位置超出编辑区域时的位置
        if(elePos.x+childW > parentW){
            left = left - childW;
        }
        if(elePos.y+childH > parentH){
            top = top - childH;
        }
        this.personPosi = {
            left: left+'px',
            top: top+'px'
        };
        this.stateEmpty();
    }

    /**
     * 计算当前'@'数
     * 
     * @memberof RichTextEditor
     */
    public calculate(){
        const atSymbol:any = this.editor.dom.select('span');
        let id:number = 0;
        atSymbol.forEach((item:any)=>{
            if(Object.is(item.className,'at-text') || Object.is(item.className,'fake-at')){
                const atId = parseInt(item.id.substring(item.id.length-1));
                if(atId>id){
                    id = atId;
                }
            }
        })
        this.atNumber = id;
    }
    
    /**
     * 拼接 @ 人员的id
     * 
     * @memberof RichTextEditor
     */
    public jointSelectPersonId(){
        const atSymbol:any = this.editor.dom.select('span');
        let noticeusers:string = "";
        atSymbol.forEach((item:any)=>{
            if(Object.is(item.className,'at-text')){
                LogUtil.log(item);
                noticeusers += item.getAttribute('noticeusers')+',';
            }
        })
        noticeusers = noticeusers.substring(0,noticeusers.length-1);
        return noticeusers
    }

    /**
     * 点击"@"的人员时，&nbsp必须单独放入一个span中，用于区分输入区域
     * 
     * @param $event 选中人员数据
     * @memberof RichTextEditor
     */
    public selectPerson($event: any){
        this.showSelect = 'hidden';
        let selection = this.lastSelection.selection;
        let range = this.lastSelection.range;
        let spanNode1: any= document.createElement('span');
        let spanNode2: any = document.createElement('span');
        spanNode1.className = 'at-text';
        spanNode1.innerHTML = '@' + $event.text;
        spanNode1.id = 'at-text'+this.atNumber;
        spanNode1.setAttribute('noticeusers',$event.value);
        spanNode1.style = 'color: #108cee;';
        spanNode2.innerHTML = '&nbsp';
        // 将生成内容打包放在 Fragment 中，并获取生成内容的最后一个节点，也就是空格。
        let frag = document.createDocumentFragment(),
            node, lastNode
        frag.appendChild(spanNode1)
        while ((node = spanNode2.firstChild)) {
            lastNode = frag.appendChild(node)
        }
        // 将 Fragment 中的内容放入 range 中，并将光标放在空格之后。
        range.insertNode(frag)
        selection.collapse(lastNode, 1)
        //删除输入@时创建的节点
        this.editor.dom.remove('fake-at'+this.atNumber);
        selection.collapseToEnd();
        this.editor.focus();
    }

    /**
     * 上下键进行人员选择
     * 
     * @memberof RichTextEditor
     */
    public keyboardSelect(keyCode: number){
        //上键
        if(keyCode === 38){
            this.personNumber--;
            this.changeState(this.personNumber);
            if(this.personNumber < 0){
                this.personNumber = -1;
            }
        }
        //下键
        if(keyCode === 40){
            this.personNumber++;
            this.changeState(this.personNumber);
            if(this.personNumber > this.items.length){
                this.personNumber = this.items.length;
            }
        }
        //手动触发滚动事件
        const ele: any = this.$el.getElementsByClassName('select-person')[0];
        ele.scrollTo(0,this.personNumber*24);
    }

    /**
     * 鼠标移动选择
     * 
     * @memberof RichTextEditor
     */
    public onMousevoer(index: number){
        this.changeState(index);
        this.personNumber = index;
    }

    /**
     * 更改选中状态
     * 
     * @memberof RichTextEditor
     */
    public changeState(index: number){
        let items: Array<any> = [];
        this.items.forEach((item: any,i: number)=>{
            if(index === i){
                item.selectState = true;
            }else{
                item.selectState = false;
            }
            items.push(item);
        })
        this.items = items;
    }

    /**
     * 初始化选中状态
     * 
     * @memberof RichTextEditor
     */
    public stateEmpty(){
        this.items = [...this.personList];
        this.items.forEach((item: any,i: number)=>{
            item.selectState = false;
        })
        this.personSearchText = ''; 
        this.personNumber = -1;
        this.keyboardSelect(0);
    }

    /**
     * 回车确认所选数据
     * 
     * @memberof RichTextEditor
     */
    public enterSelect(){
        this.items.forEach((item:any)=>{
            if(item.selectState == true){
                this.selectPerson(item);
            }
        })
    }

    /**
     * 点击富文本外部区域时关闭列表(除点击搜索框)
     * 
     * @memberof RichTextEditor
     */
    public onClick($event: any){
        const activeEle: any = document.activeElement;
        if(activeEle.id == 'person-search'+this.id){
           return;
        }
        this.showSelect = 'hidden';
    }

    /**
     * 点击搜索框后提供的按键功能
     * 
     * @memberof RichTextEditor
     */
    public keyDown($event: any){
        if($event.keyCode == 38 || $event.keyCode == 40){
            if(Object.is(this.showSelect,'visible')){
                //取消默认动作
                $event.preventDefault ? $event.preventDefault() : $event.returnValue = false;
                //上下键进行选择
                this.keyboardSelect($event.keyCode);
            }
        }else if($event.keyCode == 13){
            if(Object.is(this.showSelect,'visible')){
                //取消默认动作
                $event.preventDefault ? $event.preventDefault() : $event.returnValue = false;
                //回车确认上下键所选数据
                this.enterSelect();
            }
        }
    }

    /**
     * 应用模板参数处理
     * 
     * @memberof RichTextEditor
     */
    public getTemplParams(){
        let data: any = {};
        let _param = this.viewparams ? JSON.parse(JSON.stringify(this.viewparams)) : {};
        let _context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
        if (this.localParam && Object.keys(this.localParam).length >0){
            data = this.$util.computedNavData({}, _context, _param, this.localParam);
        }
        return data;
    }

    /**
     * 获取应用模板数据集
     * 
     * @memberof RichTextEditor
     */
    public async appTemplateData() {
        const templParams = this.getTemplParams();
        let appTemplate: Array<any> = []
        const response: any = await this.userTplService.FetchDefault({},templParams);
        if(response && response.status === 200){
            const { data: _data } = response;
            _data.forEach((item:any)=>{
                if(Object.is(item.account,templParams.account) || Object.is(item.ibizpublic,'1')){
                    appTemplate.push(item);
                }
            });
            this.appTemplate = appTemplate;
        }
    }

    /**
     * 打开气泡时
     * 
     * @memberof RichTextEditor
     */
    public openPoper(){
        this.templateTitle = '';
        this.single = false;
    }

    /**
     * 保存模板
     * 
     * @memberof RichTextEditor
     */
    public async saveTemplate(){
        let templParams = this.getTemplParams();
        const templateTitle = this.templateTitle;
        const templateContent = this.editor.getContent();
        templParams.ibizpublic = this.single == true? '1' : '0';
        if(!templateContent || Object.is(templateContent,'')){
            this.$throw(this.$t('app.components.appCheckBox.content'),'saveTemplate');
            return;
        }
        if(!templateTitle || Object.is(templateTitle,'')){
            this.$throw(this.$t('app.components.appCheckBox.title'),'saveTemplate');
            return;
        }
        templParams.title = templateTitle;
        templParams.content = templateContent;
        const response: any = await this.userTplService.Create({}, templParams);
        if(response && response.status === 200){
            this.$success(this.$t('app.components.appCheckBox.success'),'saveTemplate');
        }else{
            this.$throw(this.$t('app.components.appCheckBox.error'),'saveTemplate');
        }
        this.appTemplateData();
        let propip: any = this.$refs.propip;
        propip.handleMouseleave();
    }

    /**
     * 取消
     */
    public onCancel(){
        let propip: any = this.$refs.propip;
        propip.handleMouseleave();
    }

    /**
     * 应用模板填充
     * 
     * @param content 应用模板内容
     * @memberof RichTextEditor
     */
    public appTemplateFill(content: any){
        this.editor.setContent(content == null ? '' : content);
    }

    /**
     * 删除应用模板
     * 
     * @param event 选中的应用模板
     */
    public async removeAppTemplate(event: any){
        let context: any = {};
        context.usertpl = event.id;
        const response: any = await this.userTplService.Remove(context,{});
        if(response && response.status === 200){
            this.$success(this.$t('app.components.appCheckBox.delSuccess'),'removeAppTemplate');
        }else{
            this.$throw(this.$t('app.components.appCheckBox.delError'),'removeAppTemplate');
        }
        this.appTemplateData();
    }

    /**
     * 上传文件
     * 
     * @param url 路径
     * @param formData 文件对象 
     * @memberof RichTextEditor
     */
    public uploadFile(url: string, formData: any) {
        let _this = this;
        const subject: Subject<any> = new Subject<any>();
        axios({
            method: 'post',
            url: url,
            data: formData,
            headers: { 'Content-Type': 'image/png', 'Accept': 'application/json' },
        }).then((response: any) => {
            if (response.status === 200) {
                subject.next(response.data);
            } else {
                subject.error(response);
            }
        }).catch((response: any) => {
            subject.error(response);
        });
        return subject;
    }

    /**
     *获取上传，导出参数
     *
     *@memberof RichTextEditor
     */
    public getParams(){
        let uploadparams: any = this.uploadparams ? JSON.parse(JSON.stringify(this.uploadparams)) : '';
        let exportparams: any = this.exportparams ? JSON.parse(JSON.stringify(this.exportparams)) : '';

        let upload_params: Array<string> = [];
        let export_params: Array<string> = [];

        let param:any = this.viewparams;
        let context:any = this.context;
        let _data:any = this.data ? JSON.parse(JSON.stringify(this.data)) : {};

        if (this.uploadparams && !Object.is(this.uploadparams, '')) {
            upload_params = this.$util.computedNavData(_data,param,context,uploadparams);    
        }
        if (this.exportparams && !Object.is(this.exportparams, '')) {
            export_params = this.$util.computedNavData(_data,param,context,exportparams);
        }
        
        this.upload_params = [];
        this.export_params = [];

        for (const item in upload_params) {
            this.upload_params.push({
                [item]:upload_params[item]
            })
        }
        for (const item in export_params) {
            this.export_params.push({
                [item]:export_params[item]
            })
        }
    }

    /**
     *获取上传，导出参数
     *
     *@memberof RichTextEditor
     */
    public readyUserItems() {
        this.codeListService.getItems('UserRealName', this.context ? JSON.parse(JSON.stringify(this.context)) : {}).then((res:any) => {
            this.personList = res;
        }).catch((error:any) => {
            
        })
    }

    /**
     * 根据搜索条件查询人员列表
     * 
     * @memberof RichTextEditor
     */
    public personSearch(){
        let items:Array<any> = [];
        if(this.personList && this.personList.length > 0){
            this.personList.forEach((item: any)=>{
                if(item.text.search(this.personSearchText) !== -1){
                    items.push(item);
                }
            })
        }
        this.items = items;
    }
}
</script>
<style lang="less">
@import './rich-text-editor.less';
.tox-statusbar__text-container{
    display: none !important;
}
</style>