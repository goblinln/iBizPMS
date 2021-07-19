import { CreateElement } from 'vue';
import { Component, Prop, Vue, Emit, Watch } from 'vue-property-decorator';

@Component({})
export class AppPanelShell extends Vue {

    /**
     * 部件动态参数
     *
     * @memberof AppPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDefaultTree
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    public getPanelComponentName() {
        if (this.staticProps && this.staticProps.modelData) {
            const modelType = this.staticProps.modelData.modeltype;
            if (modelType && modelType == 'PSSYSVIEWPANEL') {
                return 'app-default-viewpanel';
            } else {
                return 'app-default-panel';
            }
        }
    }

    public render(h: CreateElement) {
        const componentName = this.getPanelComponentName();
        if (!componentName) {
            return;
        }
        return h(componentName, {
            props: {
                staticProps: this.staticProps,
                dynamicProps: this.dynamicProps
            },
            on: {
                'ctrl-event': ({ controlname, action, data }: any) => {
                    this.ctrlEvent({ controlname, action, data });
                },
                'closeView': (args: any) => {
                    this.$emit('closeView', args);
                }
            }
        })
    }
}