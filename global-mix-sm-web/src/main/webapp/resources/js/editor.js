/* global CKEDITOR */

/**
 * @authors: Marvin Charuc
 * @version 1.0
 */

'use strict';

CKEDITOR.disableAutoInline = true;
CKEDITOR.plugins.add('hcard', {
    requires: 'widget',
//    onOk: function () {
//        var element = this.element;
//        console.log("---"+element);
//        this.commitContent(element);
//    },
    init: function (editor) {
//        var cmd = new CKEDITOR.dialogCommand('detailDialog');
//        cmd.requiredContent = 'div(h-card)';
//        editor.addCommand('detail', cmd);
        CKEDITOR.dialog.add('detailDialog', function (editor) {
            return {
                title: 'Datos de Sección Detalle',
                minWidth: 250,
                minHeight: 150,
                resizable: false,
                contents: [
                    {
                        id: 'tab-data',
                        label: 'Ingreso de datos',
                        elements: [
                            {
                                type: 'text',
                                id: 'pageHide',
                                label: 'Ocultar Encabezado, Ingrese Nro. Página(s)',
                                setup: function (element) {
                                    var headerDetail = element.element.findOne('[header-detail=true]');
                                    if (headerDetail) {
                                        this.setValue(headerDetail.hasAttribute("hide-page") ? headerDetail.getAttribute("hide-page") : "");
                                    }
                                },
                                commit: function (element) {
                                    var headerDetail = element.element.findOne('[header-detail=true]');
                                    if (headerDetail) {
                                        if (this.getValue() !== "") {
                                            headerDetail.setAttribute("hide-page", this.getValue());
                                        } else {
                                            headerDetail.removeAttribute("hide-page");
                                        }
                                    }
                                }
                            }
                        ]
                    }
                ]
            };
        });
        editor.widgets.add('hcard', {
            allowedContent: 'span(!h-card); span[data-remove]; span{text-transform}; div(*)[*](*); p(*)[*](*); span[data-dataset]; span[data-tipo]; span[data-plantilla]; span[data-documento]; span[data-objeto]; span[data-campo]; span[data-id]',
            requiredContent: 'span(h-card);div(h-card)',
            pathName: 'hcard',
            editables: {
                header: {
                    selector: 'div.header'
                },
                body: {
                    selector: 'div.body'
                }
            },
//            dialog: 'detailDialog',
//            edit: function () {
//                var el = $(this.element.$);
//                return (el[0].dataset.detalle !== undefined);
//            },
            insert: function () {
            },
            upcast: function (el) {
                return (el.name === 'div' && el.hasClass('h-card')) || (el.name === 'span' && el.hasClass('h-card'));
            }
        });
        editor.addFeature(editor.widgets.registered.hcard);
        editor.on('paste', function (evt) {
            var data = evt.data.dataTransfer.getData('param');
            if (!data) {
                return;
            }
            if (data.tipoCampo !== undefined && data.tipoCampo === 'Boolean') {
                var idCampo = data.idCampoDocumentoPlantilla !== undefined ? data.idCampoDocumentoPlantilla : data.idCampoTipoDocumento !== undefined ? data.idCampoTipoDocumento : null;
                var detalle = '';
                detalle += '<div class="h-card" data-id="' + idCampo + '" data-boolean="true"><span style="font-size:10px"><strong>' + data.nombreEtiqueta + '</strong></span>';
                detalle += '<div class="header" view="true">Si es verdadero se muestra esto</div>';
                detalle += '<div class="body" view="false">Si es falso se muestra esto</div>';
                detalle += '</div>';
                evt.data.dataValue = detalle;
            } else if (data.idCampoDocumentoPlantilla !== undefined) {
                if (data.detalle !== undefined && data.detalle !== null) {
                    //editor.execCommand('detail');
                    var detalle = '';
                    detalle += '<div class="h-card" data-detalle="' + data.nombreEtiqueta + '" data-dataset="' + data.dataset + '">';
                    //detalle += '<div class="header" header-detail="true"></div>';
                    detalle += '<div class="body" body-detail="true">arrastre las etiquetas del detalle</div>';
                    detalle += '</div>';
                    evt.data.dataValue = detalle;
                } else {
                    evt.data.dataValue = '<span data-remove="true"><span style="font-size:10px"><span class="h-card" ' + (data.estado === "Children" ? 'data-dataset="' + data.dataset + '"' : '') + ' data-objeto="' + (data.nombreObjeto !== null ? data.nombreObjeto : "-") + '" data-campo="' + data.nombreCampo + '" data-id="' + data.idCampoDocumentoPlantilla + '">' + data.nombreEtiqueta + '</span></span></span>';
                }
            } else if (data.idCampoTipoDocumento !== undefined) {
                evt.data.dataValue = '<span data-remove="true"><span style="font-size:10px"><span class="h-card" data-tipo="true" data-campo="' + data.nombreCampo + '" data-id="' + data.idCampoTipoDocumento + '">' + data.nombreEtiqueta + '</span></span></span>';
            } else if (data.idDocumentoPlantilla !== undefined) {
                evt.data.dataValue = '<span data-remove="true"><span style="font-size:10px"><span class="h-card" data-plantilla="' + data.idDocumentoPlantilla + '" data-campo="' + data.nombre + '" data-id="' + (data.idDocumentoPlantilla * -1000) + '">' + data.nombre + '</span></span></span>';
            }
        });
    },
    onLoad: function () {
        CKEDITOR.addCss(
                '.cke_editable div.h-card:not(:focus){border-top: 0.09rem dotted #C0C0C0; border-bottom: 0.09rem dotted #C0C0C0;}' +
                '.cke_editable div.h-card:hover {background-color: #ffefc4;}' +
                '.cke_editable div div {background-color: #FFFFFF;}'
                );
    }
});

CKEDITOR.on('instanceReady', function () {
    if (CKEDITOR.document.getById('paramsList')) {
        CKEDITOR.document.getById('paramsList').on('dragstart', function (evt) {
            var target = evt.data.getTarget().getAscendant('div', true);
            CKEDITOR.plugins.clipboard.initDragDataTransfer(evt);
            var dataTransfer = evt.data.dataTransfer;
            if (target.data('children') === null) {
                dataTransfer.setData('param', PARAMETERS[target.data('param')]);
            } else {
                dataTransfer.setData('param', PARAMETERS[target.data('param')].detalle[target.data('children')]);
            }
            dataTransfer.setData('text/html', target.getText());
        });
    }
});

var extraPluginsEditor = 'hcard,lineheight,texttransform,textindent,docProperties';
var elemFooter = document.getElementById("editorFooter");
if (elemFooter !== undefined && elemFooter !== null) {
    CKEDITOR.inline('editorFooter', {
        extraPlugins: 'hcard,lineheight,texttransform,textindent',
        language: 'es-mx',
        toolbarGroups: [
            {name: 'clipboard', groups: ['clipboard', 'undo']},
            {name: 'styles', groups: ['styles']},
            {name: 'colors', groups: ['colors']},
            {name: 'basicstyles', groups: ['basicstyles', 'texttransform', 'cleanup']},
            {name: 'paragraph', groups: ['list', 'indent', 'textindent', 'blocks', 'align', 'bidi']},
            {name: 'forms', groups: ['forms']},
            {name: 'links', groups: ['links']},
            {name: 'insert', groups: ['insert']},
            {name: 'document', groups: ['mode', 'document', 'doctools']},
            {name: 'tools', groups: ['tools']},
            {name: 'editing', groups: ['find', 'selection', 'spellchecker', 'editing']},
            {name: 'others', groups: ['others']},
            {name: 'about', groups: ['about']}
        ],
        removeButtons: 'Source,Sourcedialog,Styles,Maximize,Preview,Image,BidiLtr,BidiRtl,Print,NewPage,Save,Templates,Paste,PasteText,PasteFromWord,Find,Replace,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,Subscript,Superscript,Strike,CreateDiv,Language,Anchor,Flash,HorizontalRule,Smiley,SpecialChar,PageBreak,Iframe,Googledocs,ShowBlocks,save-to-pdf,About',
        removePlugins: 'elementspath, div',
        resize_enabled: false
    });
} else {
    extraPluginsEditor = 'hcard,lineheight,texttransform,textindent';
}

var editorHtml = CKEDITOR.replace('editorDoc', {
    extraPlugins: extraPluginsEditor,
    language: 'es-mx',
//    width: '836px',
//    height: '500px', 
    toolbarGroups: [
        {name: 'clipboard', groups: ['clipboard', 'undo']},
        {name: 'styles', groups: ['styles']},
        {name: 'colors', groups: ['colors']},
        {name: 'basicstyles', groups: ['basicstyles', 'texttransform', 'cleanup']},
        {name: 'paragraph', groups: ['list', 'indent', 'textindent', 'blocks', 'align', 'bidi']},
        {name: 'forms', groups: ['forms']},
        {name: 'links', groups: ['links']},
        {name: 'insert', groups: ['insert']},
        {name: 'document', groups: ['mode', 'document', 'doctools']},
        {name: 'tools', groups: ['tools']},
        {name: 'editing', groups: ['find', 'selection', 'spellchecker', 'editing']},
        {name: 'others', groups: ['others']},
        {name: 'about', groups: ['about']}
    ],
    removeButtons: 'Source,Sourcedialog,Styles,Maximize,Preview,Image,BidiLtr,BidiRtl,Print,NewPage,Save,Templates,Paste,PasteText,PasteFromWord,Find,Replace,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,Subscript,Superscript,Strike,CreateDiv,Language,Anchor,Flash,HorizontalRule,Smiley,SpecialChar,PageBreak,Iframe,Googledocs,ShowBlocks,save-to-pdf,About',
    removePlugins: 'elementspath, div',
    resize_enabled: false
//    extraAllowedContent: 'for(*)[*]{*};',
//    enterMode: CKEDITOR.ENTER_BR
});

var PARAMETERS;
var PARAMETERS_USED;
var documentoPlantilla = null;
var idDocumentoExpediente = null;
var jsonFooterPlantilla = null;
var htmlFooterPlantilla = null;

function clickElement(pElement){
    if (document.getElementById(pElement)) {
        document.getElementById(pElement).click();
    }
}

function loadParams(input) {
    if (document.getElementById(input) !== undefined) {
        var inputGet = document.getElementById(input).value;
        if (inputGet !== null && inputGet !== undefined) {
            PARAMETERS = inputGet !== '' ? JSON.parse(inputGet) : [];
        }
        if (PARAMETERS !== undefined && PARAMETERS.length > 0) {
            PARAMETERS_USED = [];
            PARAMETERS.forEach(function (row) {
                if (row.utilizado !== undefined && row.utilizado) {
                    PARAMETERS_USED.push(row.idCampoDocumentoPlantilla);
                }
            });
        }
    } else {
        PARAMETERS = [];
    }
}

function loadTemplate(inputHtml, editor, pDocumentoPlantilla, pDataReport) {
    if (document.getElementById(inputHtml)) {
        documentoPlantilla = pDocumentoPlantilla !== undefined ? JSON.parse(pDocumentoPlantilla.substring(1, pDocumentoPlantilla.length - 1)) : null;
        pDataReport = (pDataReport !== undefined && pDataReport !== null) ? JSON.parse(pDataReport.substring(1, pDataReport.length - 1)) : null;
        var html = (pDataReport !== null) ? pDataReport.definicionHtml : document.getElementById(inputHtml).value;
        var json = (pDataReport !== null) ? pDataReport.definicionJson : document.getElementById(inputHtml.replace('Html', 'Json')).value;
        idDocumentoExpediente = null;
        idDocumentoExpediente = (pDataReport !== null && pDataReport.idDocumentoExpedienteProceso !== undefined && pDataReport.idDocumentoExpedienteProceso !== null) ? pDataReport.idDocumentoExpedienteProceso : null;
        var ckeditor = CKEDITOR.instances[editor];
        if (ckeditor !== null && ckeditor !== undefined) {
            html = (html !== null && html !== undefined ? html : '');
            var dataHtml = html.split('<hr>');
            var propertiesDoc, footerDefinicion = null;
            if (dataHtml.length > 0) {
                propertiesDoc = dataHtml.length === 3 ? document.createRange().createContextualFragment(dataHtml[0]).firstChild : (dataHtml.length === 2 ? (document.createRange().createContextualFragment(dataHtml[0]).firstChild.dataset.paperType !== undefined ? document.createRange().createContextualFragment(dataHtml[0]).firstChild : (dataHtml.length === 1 ? null : document.createRange().createContextualFragment(dataHtml[0]).firstChild)) : null);
                footerDefinicion = dataHtml.length === 3 ? dataHtml[2] : (dataHtml.length === 2 ? (document.createRange().createContextualFragment(dataHtml[0]).firstChild.dataset.paperType === undefined ? dataHtml[1] : null) : null);
                ckeditor.setData(dataHtml.length === 3 ? dataHtml[1] : (dataHtml.length === 2 ? (propertiesDoc !== null && propertiesDoc.dataset.paperType !== undefined ? dataHtml[1] : dataHtml[0]) : dataHtml[0]));
            } else {
                ckeditor.setData('');
            }
            htmlFooterPlantilla = footerDefinicion;
            ckeditor.on('contentDom', function () {
                var jsonDefault = {};
                var documentDefault = jsonDefault.document = {};
                var bodyDefault = documentDefault.body = {pageType: "Carta", pageSize: {width: 612, height: 792}, pageOrientation: "portrait", reportHeader: false, reportFooter: false, footerImage: true, marginSymmetric: false, pageMargins: [85.04, 70.87, 85.04, 70.87]};
                var jsonReporte = json !== null && json !== undefined ? json.trim() !== '' ? JSON.parse(json.trim()).document.body : bodyDefault : bodyDefault;
                var body = ckeditor.document.$.body;
                if (propertiesDoc !== null) {
                    body.dataset.paperType = propertiesDoc.dataset.paperType;
                    body.dataset.paperWidth = propertiesDoc.dataset.paperWidth;
                    body.dataset.paperHeight = propertiesDoc.dataset.paperHeight ;
                    body.dataset.paperOrientation = propertiesDoc.dataset.paperOrientation;
                    body.dataset.reportHeader = propertiesDoc.dataset.reportHeader;
                    body.dataset.reportFooter = propertiesDoc.dataset.reportFooter;
                    body.dataset.footerImage = propertiesDoc.dataset.footerImage;
                    body.dataset.marginSymmetric = propertiesDoc.dataset.marginSymmetric;
                    if (propertiesDoc.style) {
                        body.style = propertiesDoc.getAttribute('style');
                    }
                } else {
                    body.dataset.paperType = jsonReporte.pageType;
                    body.dataset.paperWidth = jsonReporte.pageSize.width;
                    body.dataset.paperHeight = jsonReporte.pageSize.height;
                    body.dataset.paperOrientation = jsonReporte.pageOrientation;
                    body.dataset.reportHeader = jsonReporte.reportHeader !== undefined ? jsonReporte.reportHeader.toString() : 'false';
                    body.dataset.reportFooter = jsonReporte.reportFooter !== undefined ? jsonReporte.reportFooter.toString() : 'false';
                    body.dataset.footerImage = jsonReporte.footerImage !== undefined ? jsonReporte.footerImage.toString() : 'true';
                    body.dataset.marginSymmetric = jsonReporte.marginSymmetric !== undefined ? jsonReporte.marginSymmetric.toString() : 'false';
                    jsonFooterPlantilla = jsonReporte.textFooter !== undefined ? jsonReporte.textFooter : null;
                    var margins = jsonReporte.pageMargins;
                    var margenes = convertValue(margins[1], 'pt', 'px') + 'px ';
                    margenes += convertValue(margins[2], 'pt', 'px') + 'px ';
                    margenes += convertValue(margins[3], 'pt', 'px') + 'px ';
                    margenes += convertValue(margins[0], 'pt', 'px') + 'px ';
                    body.setAttribute('style', ('margin: ' + margenes).trim());
                }
                var footer = document.getElementById("editorFooter");
                if (footer !== undefined && footer !== null) {
                    var editorDoc = document.getElementById("cke_editorDoc");
                    var ckeditorFooter = CKEDITOR.instances['editorFooter'];
                    ckeditorFooter.setData(footerDefinicion !== null ? footerDefinicion : '');
                    footer.style.display = (footerDefinicion !== null ? 'block' : 'none');
                    if (body.dataset.footerImage === 'true') {
                        if (!footer.classList.contains('editorFooter')) {
                            footer.classList.add('editorFooter');
                        }
                    } else {
                        if (footer.classList.contains('editorFooter')) {
                            footer.classList.remove('editorFooter');
                        }
                    }
                    if (editorDoc !== undefined) {
                        if (footerDefinicion !== null) {
                            editorDoc.classList.add("editorWithFooter");
                        } else {
                            editorDoc.classList.remove("editorWithFooter");
                        }
                    }
                }
            });
        }
    }
}

function setValueTemplate(form, inputGet, inputSet) {
    var ckeditor = CKEDITOR.instances[inputGet];
    if (ckeditor !== undefined && ckeditor.getData() !== '') {
        if (document.getElementById(form + ':idDocumentoExpedienteProceso')) {
            document.getElementById(form + ':idDocumentoExpedienteProceso').value = (idDocumentoExpediente);
        }
        //document.getElementById(form + ':' + inputSet).value = ckeditor.getData();
        PARAMETERS_USED = [];
        var jsonReporte = {};
        var documentReporte = jsonReporte.document = {};
        var bodyReporte = documentReporte.body = {};
        var content = [];
        parseHtml(content, ckeditor.getData());
        var body = $(ckeditor)[0].document.$.body;
        bodyReporte.pageType = body.dataset.paperType !== undefined ? body.dataset.paperType : 'Carta';
        bodyReporte.pageSize = {};
        bodyReporte.pageSize.width = body.dataset.paperWidth !== undefined ? +body.dataset.paperWidth : 612;
        bodyReporte.pageSize.height = body.dataset.paperHeight !== undefined ? +body.dataset.paperHeight : 792;
        bodyReporte.pageOrientation = body.dataset.paperOrientation !== undefined ? body.dataset.paperOrientation : 'portrait';
        var left, top, right, bottom;
        bodyReporte.pageMargins = [];
        if (body.getAttribute('style')) {
            body.getAttribute('style').split(';').forEach(function (row) {
                if (row.includes('margin:')) {
                    row = row.replace('margin:', '').trim().split(/[\s]+/);
                    if (row.length === 1) {
                        top = bottom = left = right = (convertValue(row[0], 'px', 'pt'));
                    } else if (row.length === 2) {
                        left = right = (convertValue(row[1], 'px', 'pt'));
                        top = bottom = (convertValue(row[0], 'px', 'pt'));
                    } else if (row.length === 3) {
                        left = right = (convertValue(row[1], 'px', 'pt'));
                        top = (convertValue(row[0], 'px', 'pt'));
                        bottom = (convertValue(row[2], 'px', 'pt'));
                    } else if (row.length === 4) {
                        left = (convertValue(row[3], 'px', 'pt'));
                        top = (convertValue(row[0], 'px', 'pt'));
                        right = (convertValue(row[1], 'px', 'pt'));
                        bottom = (convertValue(row[2], 'px', 'pt'));
                    }
                } else {
                    if (row.includes('margin-left:')) {
                        left = (convertValue(row, 'px', 'pt'));
                    }
                    if (row.includes('margin-top:')) {
                        top = (convertValue(row, 'px', 'pt'));
                    }
                    if (row.includes('margin-right:')) {
                        right = (convertValue(row, 'px', 'pt'));
                    }
                    if (row.includes('margin-bottom:')) {
                        bottom = (convertValue(row, 'px', 'pt'));
                    }
                }
            });
        }
        bodyReporte.pageMargins.push(left !== undefined ? +left : 85.04);
        bodyReporte.pageMargins.push(top !== undefined ? +top : 70.87);
        bodyReporte.pageMargins.push(right !== undefined ? +right : 85.04);
        bodyReporte.pageMargins.push(bottom !== undefined ? +bottom : 70.87);
        bodyReporte.reportHeader = body.dataset.reportHeader !== undefined ? body.dataset.reportHeader === 'true' : false;
        bodyReporte.reportFooter = body.dataset.reportFooter !== undefined ? body.dataset.reportFooter === 'true' : false;
        bodyReporte.footerImage = body.dataset.footerImage !== undefined ? body.dataset.footerImage === 'true' : false;
        bodyReporte.marginSymmetric = body.dataset.marginSymmetric !== undefined ? body.dataset.marginSymmetric === 'true' : false;
        var propertiesDoc = document.createElement("p");
        propertiesDoc.setAttribute("id", "propertiesDoc");
        propertiesDoc.dataset.paperType = body.dataset.paperType;
        propertiesDoc.dataset.paperWidth = body.dataset.paperWidth;
        propertiesDoc.dataset.paperHeight = body.dataset.paperHeight;
        propertiesDoc.dataset.paperOrientation = body.dataset.paperOrientation;
        propertiesDoc.dataset.reportHeader = body.dataset.reportHeader;
        propertiesDoc.dataset.reportFooter = body.dataset.reportFooter;
        propertiesDoc.dataset.footerImage = body.dataset.footerImage !== undefined ? body.dataset.footerImage : 'false';
        propertiesDoc.dataset.marginSymmetric = body.dataset.marginSymmetric !== undefined ? body.dataset.marginSymmetric : 'false';
        if(body.getAttribute('style')){
            propertiesDoc.style = body.getAttribute('style');
        }
        var definicionHtml = propertiesDoc.outerHTML;
        definicionHtml += '<hr>';
        definicionHtml += ckeditor.getData();
        if (bodyReporte.reportFooter) {
            bodyReporte.textFooter = jsonFooterPlantilla !== null ? jsonFooterPlantilla : undefined;
            var ckeditorFooter = CKEDITOR.instances['editorFooter'];
            if (ckeditorFooter !== undefined && ckeditorFooter !== null && ckeditorFooter.getData() !== '') {
                var textFooter = {};
                definicionHtml += '<hr>';
                definicionHtml += ckeditorFooter.getData();
                var jsonFooter = [];
                parseHtml(jsonFooter, ckeditorFooter.getData());
                if (jsonFooter.length > 0) {
                    delete jsonFooter[0].margin;
                    textFooter = jsonFooter[0];
                    var footerPosition = bodyReporte.pageMargins[3] > 55 ? roundNumber(bodyReporte.pageMargins[3] - 55) : 0;
                    textFooter.absolutePosition = {y: footerPosition};
                }
                bodyReporte.textFooter = textFooter;
            } else {
                if (htmlFooterPlantilla !== null) {
                    definicionHtml += '<hr>';
                    definicionHtml += htmlFooterPlantilla;
                    if (bodyReporte.textFooter === undefined || bodyReporte.textFooter === null) {
                        var textFooter = {};
                        var jsonFooter = [];
                        parseHtml(jsonFooter, htmlFooterPlantilla);
                        if (jsonFooter.length > 0) {
                            delete jsonFooter[0].margin;
                            textFooter = jsonFooter[0];
                            var footerPosition = bodyReporte.pageMargins[3] > 55 ? roundNumber(bodyReporte.pageMargins[3] - 55) : 0;
                            textFooter.absolutePosition = {y: footerPosition};
                        }
                        bodyReporte.textFooter = textFooter;
                    }
                }
            }
        }
        if (ckeditor.getData() !== '') {
            document.getElementById(form + ':' + inputSet).value = definicionHtml;
        } else {
            document.getElementById(form + ':' + inputSet).value = null;
        }
        bodyReporte.content = content;
        document.getElementById(form + ':' + inputSet.replace('Html', 'Json')).value = JSON.stringify(jsonReporte);
        if (document.getElementById(form + ':tamanioPapel')) {
            document.getElementById(form + ':tamanioPapel').value = bodyReporte.pageType;
        }
        if (document.getElementById(form + ':tipoDoc') && documentoPlantilla !== null) {
            document.getElementById(form + ':tipoDoc').value = JSON.stringify(documentoPlantilla);
        }
        document.getElementById(form + ':paramsUsed').value = (documentoPlantilla === null ? PARAMETERS_USED.toString() : JSON.stringify(PARAMETERS_USED));
    } else {
        document.getElementById(form + ':' + inputSet.replace('Html', 'Json')).value = null;
    }
}

function setResetValueTemplate(form, inputGet, inputSet) {
    var ckeditor = CKEDITOR.instances[inputGet];
    if (ckeditor !== null && ckeditor !== undefined) {
        ckeditor.setData('');
    }
    if (document.getElementById(form + ':idDocumentoExpedienteProceso')) {
        document.getElementById(form + ':idDocumentoExpedienteProceso').value = null;
    }
    if (document.getElementById(form + ':' + inputSet)) {
        document.getElementById(form + ':' + inputSet).value = null;
        document.getElementById(form + ':' + inputSet.replace('Html', 'Json')).value = null;
        jsonFooterPlantilla = null;
        htmlFooterPlantilla = null;
    }
    if (document.getElementById(form + ':tamanioPapel')) {
        document.getElementById(form + ':tamanioPapel').value = null;
    }
    if (document.getElementById(form + ':tipoDoc')) {
        document.getElementById(form + ':tipoDoc').value = null;
    }
    if (document.getElementById(form + ':paramsUsed')) {
        document.getElementById(form + ':paramsUsed').value = null;
    }
}

function htmlToWord(editor) {
    var ckeditor = CKEDITOR.instances[editor];
    if (ckeditor !== undefined && ckeditor.getData() !== '') {
        var body = ckeditor.getData();
        var header = "<html xmlns:o='urn:schemas-microsoft-com:office:office' " +
                "xmlns:w='urn:schemas-microsoft-com:office:word' " +
                "xmlns='http://www.w3.org/TR/REC-html40'>" +
                "<head><meta charset='utf-8'><title>SIAMP</title></head><body>";
        var footer = "</body></html>";
        var sourceHTML = header + body + footer;

        var source = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(sourceHTML);
        var fileDownload = document.createElement("a");
        document.body.appendChild(fileDownload);
        fileDownload.href = source;
        fileDownload.download = 'document.doc';
        fileDownload.click();
        document.body.removeChild(fileDownload);
    }
}

function htmlToWord2(editor) {
    var ckeditor = CKEDITOR.instances[editor];
    if (ckeditor !== undefined && ckeditor.getData() !== '') {
        var body = $(ckeditor)[0].document.$.body;
        var pageOrientation = body.dataset.paperOrientation !== undefined ? body.dataset.paperOrientation : 'portrait';
        var left, top, right, bottom;
        if (body.getAttribute('style')) {
            body.getAttribute('style').split(';').forEach(function (row) {
                if (row.includes('margin:')) {
                    row = row.replace('margin:', '').trim().split(/[\s]+/);
                    if (row.length === 1) {
                        top = bottom = left = right = (convertValue(row[0], 'px', 'pt'));
                    } else if (row.length === 2) {
                        left = right = (convertValue(row[1], 'px', 'pt'));
                        top = bottom = (convertValue(row[0], 'px', 'pt'));
                    } else if (row.length === 3) {
                        left = right = (convertValue(row[1], 'px', 'pt'));
                        top = (convertValue(row[0], 'px', 'pt'));
                        bottom = (convertValue(row[2], 'px', 'pt'));
                    } else if (row.length === 4) {
                        left = (convertValue(row[3], 'px', 'pt'));
                        top = (convertValue(row[0], 'px', 'pt'));
                        right = (convertValue(row[1], 'px', 'pt'));
                        bottom = (convertValue(row[2], 'px', 'pt'));
                    }
                } else {
                    if (row.includes('margin-left:')) {
                        left = (convertValue(row, 'px', 'pt'));
                    }
                    if (row.includes('margin-top:')) {
                        top = (convertValue(row, 'px', 'pt'));
                    }
                    if (row.includes('margin-right:')) {
                        right = (convertValue(row, 'px', 'pt'));
                    }
                    if (row.includes('margin-bottom:')) {
                        bottom = (convertValue(row, 'px', 'pt'));
                    }
                }
            });
        }
        var mLeft = (left !== undefined ? +left : 85.04) * 20;
        var mTop = (top !== undefined ? +top : 70.87) * 20;
        var mRight = (right !== undefined ? +right : 85.04) * 20;
        var mBottom = (bottom !== undefined ? +bottom : 70.87) * 20;
        //var margins = {top: mTop, right: mRight, bottom: mBottom, left: mLeft};
        var bodyHtml = ckeditor.getData();
        var header = "<!DOCTYPE html><html><head><meta charset='utf-8'><title>SIAMP</title></head><body>";
        var footer = "</body></html>";
        var sourceHTML = header + bodyHtml + footer;
        var converted = htmlDocx.asBlob(sourceHTML, {orientation: pageOrientation, margins: {top: mTop, right: mRight, bottom: mBottom, left: mLeft}});
        saveAs(converted, 'test.docx');
    }
}

function convertValue(value, tipoEntrada, tipoSalida) {
    var result = value;
    if (value !== null && value !== undefined && value !== '') {
        result = result.toString().trim().replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
        if (tipoEntrada === 'cm') {
            //cm to point - cm to pixel
            if (tipoSalida === 'px') {
                result = Math.round((((((+result / 2.54) * 72) * 96) / 72) + Number.EPSILON) * 100) / 100;
            } else if (tipoSalida === 'pt') {
                result = Math.round((((+result / 2.54) * 72) + Number.EPSILON) * 100) / 100;
            }
        }
        if (tipoEntrada === 'px') {
            //px to point - px to cm
            if (tipoSalida === 'cm') {
                result = Math.round((((((+result * 72) / 96) / 72) * 2.54) + Number.EPSILON) * 100) / 100;
            } else if (tipoSalida === 'pt') {
                result = Math.round((((+result * 72) / 96) + Number.EPSILON) * 100) / 100;
            }
        }
        if (tipoEntrada === 'pt') {
            //point to px || point to cm
            if (tipoSalida === 'cm') {
                result = Math.round((((+result / 72) * 2.54) + Number.EPSILON) * 100) / 100;
            } else if (tipoSalida === 'px') {
                result = Math.round((((+result * 96) / 72) + Number.EPSILON) * 100) / 100;
            }
        }
        return +(result.toFixed(2));
    }
    return result;
}

function roundNumber(pNumber) {
    return +(Math.round(pNumber * 100) / 100).toFixed(2);
}

function isArray(object) {
    return Object.prototype.toString.call(object) === '[object Array]';
}

function parseHtml(container, htmlText) {
    var html = $(htmlText.replace(/\t/g, "").replace(/(\n+)?/g, ""));
    var parent = createParagraph();
    for (var i = 0; i < html.length; i++) {
        parseElement(container, html.get(i), parent);
    }
}

function createParagraph() {
    return {text: []};
}

function parseElement(container, element, parent, styles) {
    if (!styles) {
        styles = [];
    }
    if (element.nodeName.toLowerCase() !== '#text') {
        if (!element.getAttribute("data-remove") && element.getAttribute && element.nodeName.toLowerCase() !== 'th' && element.nodeName.toLowerCase() !== 'td') {
            var nodeStyle = element.getAttribute("style");
            if (nodeStyle) {
                var elementStyles = nodeStyle.split(";");
                for (var k = 0; k < elementStyles.length; k++) {
                    styles.push(elementStyles[k]);
                }
            }
        } else {
            if (element.nodeName.toLowerCase() === 'th' || element.nodeName.toLowerCase() === 'td') {
                styles = [];
            }
        }
    }
    switch (element.nodeName.toLowerCase()) {
        case "#text":
            var objectText = {text: element.textContent.replace(/(\n+)?/g, "")};
            if (styles) {
                computeStyle(objectText, styles);
            }
            parent.text.push(objectText);
            break;
        case "a":
            parseContainer(container, element, parent, styles.concat(["color:blue;text-decoration:underline"]));
            break;
        case "b":
        case "strong":
            parseContainer(container, element, parent, styles.concat(["font-weight:bold"]));
            break;
        case "u":
            parseContainer(container, element, parent, styles.concat(["text-decoration:underline"]));
            break;
        case "em":
        case "i":
            parseContainer(container, element, parent, styles.concat(["font-style:italic"]));
            break;
        case "span":
            //verificar que etiquetas fueron agregadas al documento plantilla...
            if (element.getAttribute("data-tipo") || element.getAttribute("data-id")) {
                var tipo = element.getAttribute("data-tipo");
                var plantilla = element.getAttribute("data-plantilla");
                var doc = element.getAttribute("data-documento");
                var id = element.getAttribute("data-id");
                var idParam = ((tipo === undefined || tipo === null) ? id : null);
                var objeto = element.getAttribute("data-objeto");
                var campo = element.getAttribute("data-campo");
                var etiqueta = (objeto !== undefined && objeto !== null ? (objeto === '-' ? campo : (objeto + "_" + campo)) : campo).replace(/\s+/g, "_");
                var text = ((plantilla === undefined || plantilla === null) ? element.textContent : '{"idDocumentoPlantilla": ' + plantilla + ((doc !== undefined && doc !== null) ? (', "idDocumentoExpedienteProceso": ' + doc) : '') + '}');
                var agregar = true;
                if (idParam !== null) {
                    $.each(PARAMETERS_USED, function (index, row) {
                        if ((documentoPlantilla === null ? row : row.idCampoDocumentoPlantilla) === idParam) {
                            return (agregar = false);
                        }
                    });
                    if (agregar) {
                        var dataAdd = documentoPlantilla === null ? idParam : {idCampo: idParam, nombreEtiqueta: etiqueta, valor: text};
                        PARAMETERS_USED.push(dataAdd);
                    }
                }
                if (documentoPlantilla === null) {
                    element.textContent = "${" + etiqueta + "}";
                }
            }
            //verificar si es solo espacio...
            var agregar = true;
            var classCss = element.getAttribute("class");
            if (classCss) {
                agregar = classCss.indexOf('cke_widget_drag_handler_container') <= -1;
            }
            if (agregar) {
                parseContainer(container, element, parent, styles);
            }
            break;
        case "br":
            parent = createParagraph();
            container.push(parent);
            break;
        case "table":
        {
            var objectTable = {
                table: {
                    widths: [],
                    heights: [],
                    body: []
                },
                margin: [0, 5, 0, 5]
            };
            var border = element.getAttribute("border");
            var isBorder = false;
            if (border) {
                if (parseInt(border) === 1)
                    isBorder = true;
            }
            if (!isBorder) {
                objectTable.layout = 'noBorders';
            }
            parseContainer(objectTable.table.body, element, parent, styles);
            var widths = element.getAttribute("widths");
            if (!widths) {
                if (objectTable.table.body.length !== 0) {
                    if (objectTable.table.body[0].length !== 0) {
                        for (var col = 0; col < objectTable.table.body[0].length; col++) {
                            objectTable.table.widths.push("*");
                        }
                    }
                }
            } else {
                var width = widths.split(",");
                for (var w = 0; w < width.length; w++) {
                    objectTable.table.widths.push(width[w]);
                }
            }
            var heights = element.getAttribute("heights");
            if (!heights) {
                if (objectTable.table.body.length !== 0) {
                    //if (objectTable.table.body[0].length !== 0) {
                        for (var rowT = 0; rowT < objectTable.table.body.length; rowT++) {
                            objectTable.table.heights.push("*");
                        }
                    //}
                }
            } else {
                var height = heights.split(",");
                for (var w = 0; w < height.length; w++) {
                    objectTable.table.heights.push(height[w]);
                }
            }
            container.push(objectTable);
            break;
        }
        case "thead":
        case "tbody":
            parseContainer(container, element, parent, styles);
            break;
        case "tr":
            var row = [];
            parseContainer(row, element, parent, styles);
            container.push(row);
            break;
        case "th":
            parent = createParagraph();
            var objectStack = {stack: []};
            objectStack.stack.push(parent);
            var rspan = element.getAttribute("rowspan");
            if (rspan) {
                objectStack.rowSpan = parseInt(rspan);
            }
            var cspan = element.getAttribute("colspan");
            if (cspan) {
                objectStack.colSpan = parseInt(cspan);
            }
            var scope = element.getAttribute("scope");
            if (scope) {
                if (scope === "col" || scope === "row") {
                    objectStack.bold = true;
                    objectStack.alignment = 'center';
                }
            }
            var nodeStyle = element.getAttribute("style");
            if (nodeStyle) {
                var elementStyles = nodeStyle.split(";");
                for (var k = 0; k < elementStyles.length; k++) {
                    styles.push(elementStyles[k]);
                }
                computeStyleTable(objectStack, styles);
                styles = [];
            }
            parseContainer(objectStack.stack, element, parent, styles);
            container.push(objectStack);
            break;
        case "td":
        {

            parent = createParagraph();
            var objectStack = {stack: []};
            objectStack.stack.push(parent);
            var rspan = element.getAttribute("rowspan");
            if (rspan) {
                objectStack.rowSpan = parseInt(rspan);
            }
            var cspan = element.getAttribute("colspan");
            if (cspan) {
                objectStack.colSpan = parseInt(cspan);
            }
            var nodeStyle = element.getAttribute("style");
            if (nodeStyle) {
                var elementStyles = nodeStyle.split(";");
                for (var k = 0; k < elementStyles.length; k++) {
                    styles.push(elementStyles[k]);
                }
                computeStyleTable(objectStack, styles);
                styles = [];
            }
            parseContainer(objectStack.stack, element, parent, styles);
            container.push(objectStack);
            break;
        }
        case "h1":
        case "h2":
        case "h3":
        case "h4":
        case "h5":
        case "div":
        case "details":
        case "p":
        {
            parent = createParagraph();
            var objectStack = {stack: []};
            objectStack.stack.push(parent);
            computeStyle(objectStack, styles);
            objectStack.margin = [0, 5, 0, 5];
            parseContainer(objectStack.stack, element, parent);
            var header = element.getAttribute("header-detail");
            if (header) {
                //objectStack.headerDetail = header;
                computeAttributes(objectStack, element.attributes);
            }
            var body = element.getAttribute("body-detail");
            if (body) {
                //objectStack.bodyDetail = body;
                computeAttributes(objectStack, element.attributes);
            }
            container.push(objectStack);
            break;
        }
        case "summary":
        break;
        default:
            console.error("No se pudo analizar el nodo " + element.nodeName.toLowerCase());
            break;
    }
    return parent;
}

/**
 * 
 * @param {type} container
 * @param {type} element
 * @param {type} parent
 * @param {type} styles
 * @returns {createParagraph.editorAnonym$0}
 */
function parseContainer(container, element, parent, styles) {
    var elements = [];
    var children = element.childNodes;
    if (children.length !== 0) {
        for (var i = 0; i < children.length; i++) {
            parent = parseElement(elements, children[i], parent, styles);
            if (children[i].nodeName.toLowerCase() !== '#text') {
                var nodeStyle = children[i].getAttribute("data-remove") !== null ? null : children[i].getAttribute("style");
                if (nodeStyle) {
                    if (styles && styles.length > 0) {
                        var elementStyles = nodeStyle.split(";");
                        styles = styles.slice(0, (styles.length - elementStyles.length));
                    }
                }
            }
        }
    }
    if (elements.length !== 0) {
        for (var i = 0; i < elements.length; i++) {
            container.push(elements[i]);
        }
    }
    // verificar styles similares en hijos unirlos en el padre....
    return parent;
}

function computeAttributes(objectText, attributes) {
    for (var i = 0; i < attributes.length; i++) {
        var attr = attributes[i];
        if (attr.nodeName !== 'class') {
            var key = toCamelCase(attr.nodeName);
            var value = ['true', 'false'].indexOf(attr.nodeValue) !== -1 ? attr.nodeValue === 'true' : attr.nodeValue;
            objectText[key] = value;
        }
    }
}

function computeStyle(objectText, styles) {
    for (var i = 0; i < styles.length; i++) {
        var styleDef = styles[i].trim().toLowerCase().split(":");
        if (styleDef.length === 2) {
            var key = styleDef[0];
            var value = styleDef[1];
            switch (key) {
                case "margin":
                    value = value.replace(/(\d+)([^\d]+)/g, "$1 ").trim().split(' ');
                    // pdfMake usa un orden diferente al CSS
                    if (value.length === 1)
                        value = +value[0]; // valor unico
                    else if (value.length === 2)
                        value = [+value[1], +value[0]]; // vertical | horizontal ==> horizontal | vertical
                    else if (value.length === 3)
                        value = [+value[1], +value[0], +value[1], +value[2]]; // top | horizontal | bottom ==> left | top | right | bottom
                    else if (value.length === 4)
                        value = [+value[3], +value[0], +value[1], +value[2]]; // top | right | bottom | left ==> left | top | right | bottom
                    objectText[key] = value;
                    break;
                case "text-align":
                    objectText.alignment = value;
                    break;
                case "font-weight":
                    if (value === "bold") {
                        objectText.bold = true;
                    }
                    break;
                case "text-decoration":
                    objectText.decoration = toCamelCase(value);
                    break;
                case "font-style":
                    if (value === "italic") {
                        objectText.italics = true;
                    }
                    break;
                case "color":
                    objectText.color = parseColor(value);
                    break;
                case "background-color":
                    objectText.background = parseColor(value);
                    break;
                default:
                    if (key.indexOf("-") > -1)
                        key = toCamelCase(key);
                    if (value) {
                        value = value.replace(/["'\s]+/g, '');
                        var type = value.replace(/[^a-z]+/g, '');
                        if (type === 'px' || type === 'cm') {
                            value = value.replace(type, '');
                            if (type === 'px') {
                                value = ((value * 72) / 96);
                            } else if (type === 'cm') {
                                value = ((value / 2.54) * 72);
                            }
                        } else {
                            value = value.toString().replace(/(\d+)([^\d]+)/g, "$1 ").trim();
                            value = value.replace(/[\s]/g, ".");
                        }
                        if (!isNaN(value)) {
                            value = +(value); // turn it into a number
                            if (value >= 100) {
                                value = +(value / 100);
                            }
                        }
                        objectText[key] = value;
                    }
                    break;
            }
        }
    }
}

function computeStyleTable(objectText, styles) {
    for (var i = 0; i < styles.length; i++) {
        var styleDef = styles[i].trim().toLowerCase().split(":");
        if (styleDef.length === 2) {
            var key = styleDef[0];
            var value = styleDef[1];
            switch (key) {
                case "background-color":
                    objectText.fillColor = parseColor(value);
                    break;
                case "text-align":
                    objectText.alignment = value;
                    break;
                case "width":
                    break;
                default:
                    if (key.indexOf("-") > -1)
                        key = toCamelCase(key);
                    if (value) {
                        value = value.replace(/["'\s]+/g, '');
                        var type = value.replace(/[^a-z]+/g, '');
                        if (type === 'px' || type === 'cm') {
                            value = convertValue(value, type, 'pt');
//                            value = value.replace(type, '');
//                            if (type === 'px') {
//                                value = ((value * 72) / 96);
//                            } else if (type === 'cm') {
//                                value = ((value / 2.54) * 72);
//                            }
                        } else {
                            value = value.toString().replace(/(\d+)([^\d]+)/g, "$1 ").trim();
                            value = value.replace(/[\s]/g, ".");
                        }
                        if (!isNaN(value)) {
                            value = +(value); // turn it into a number
                            if (value >= 100) {
                                value = +(value / 100);
                            }
                        }
                        objectText[key] = value;
                    }
                    break;
            }
        }
    }
}

function toCamelCase(str) {
    return str.replace(/-([a-z])/g, function (g) {
        return g[1].toUpperCase();
    });
}

function parseColor(color) {
    color = color.replace(/\s/g, '');
    var haxRegex = new RegExp('^#([0-9a-f]{3}|[0-9a-f]{6})$');
    // e.g. `#fff` or `#ff0048`
    var rgbRegex = new RegExp('^rgb\\((\\d+),\\s*(\\d+),\\s*(\\d+)\\)$');
    // e.g. rgb(0,255,34) or rgb(22, 0, 0)
    var nameRegex = new RegExp('^[a-z]+$');
    if (haxRegex.test(color)) {
        return color;
    } else if (rgbRegex.test(color)) {
        var decimalColors = rgbRegex.exec(color).slice(1);
        for (var i = 0; i < 3; i++) {
            var decimalValue = +decimalColors[i];
            if (decimalValue > 255) {
                decimalValue = 255;
            }
            var hexString = '0' + decimalValue.toString(16);
            hexString = hexString.slice(-2);
            decimalColors[i] = hexString;
        }
        return '#' + decimalColors.join('');
    } else if (nameRegex.test(color)) {
        return color;
    } else {
        console.error('No se pudo analizar el color "' + color + '"');
        return color;
    }
}

//function previewPdf(pElement) {
//    if (document.getElementById(pElement)) {
//        var pdfData = (
//  'JVBERi0xLjcKCjEgMCBvYmogICUgZW50cnkgcG9pbnQKPDwKICAvVHlwZSAvQ2F0YWxvZwog' +
//  'IC9QYWdlcyAyIDAgUgo+PgplbmRvYmoKCjIgMCBvYmoKPDwKICAvVHlwZSAvUGFnZXMKICAv' +
//  'TWVkaWFCb3ggWyAwIDAgMjAwIDIwMCBdCiAgL0NvdW50IDEKICAvS2lkcyBbIDMgMCBSIF0K' +
//  'Pj4KZW5kb2JqCgozIDAgb2JqCjw8CiAgL1R5cGUgL1BhZ2UKICAvUGFyZW50IDIgMCBSCiAg' +
//  'L1Jlc291cmNlcyA8PAogICAgL0ZvbnQgPDwKICAgICAgL0YxIDQgMCBSIAogICAgPj4KICA+' +
//  'PgogIC9Db250ZW50cyA1IDAgUgo+PgplbmRvYmoKCjQgMCBvYmoKPDwKICAvVHlwZSAvRm9u' +
//  'dAogIC9TdWJ0eXBlIC9UeXBlMQogIC9CYXNlRm9udCAvVGltZXMtUm9tYW4KPj4KZW5kb2Jq' +
//  'Cgo1IDAgb2JqICAlIHBhZ2UgY29udGVudAo8PAogIC9MZW5ndGggNDQKPj4Kc3RyZWFtCkJU' +
//  'CjcwIDUwIFRECi9GMSAxMiBUZgooSGVsbG8sIHdvcmxkISkgVGoKRVQKZW5kc3RyZWFtCmVu' +
//  'ZG9iagoKeHJlZgowIDYKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDAwMDEwIDAwMDAwIG4g' +
//  'CjAwMDAwMDAwNzkgMDAwMDAgbiAKMDAwMDAwMDE3MyAwMDAwMCBuIAowMDAwMDAwMzAxIDAw' +
//  'MDAwIG4gCjAwMDAwMDAzODAgMDAwMDAgbiAKdHJhaWxlcgo8PAogIC9TaXplIDYKICAvUm9v' +
//  'dCAxIDAgUgo+PgpzdGFydHhyZWYKNDkyCiUlRU9G');
//        var src = document.getElementById(pElement).src;
//        document.getElementById(pElement).src = src+"?data="+pdfData;
//        console.log(document.getElementById(pElement).src);
//        //console.log(document.getElementById(pElement).value);
//    }
//}
//
//function pdfHideButton(button) {
//    console.log("....")
//    $('iframe').on('load', function () {
//        console.log($(this).contents());
//        var head = $(this).contents().find('head');
//        var css = '<style type="text/css">#' + button + '{display:none};</style>';
//        $(head).append(css);
//    });
//}
//
//function customPdfViewer() {
//    pdfHideButton('download');
//    //pdfHideButton('viewBookmark');
//}
//
//function convertDataURIToBinary(dataURI) {
//    var base64Index = dataURI.indexOf(BASE64_MARKER) + BASE64_MARKER.length;
//    var base64 = dataURI.substring(base64Index);
//    var raw = window.atob(base64);
//    var rawLength = raw.length;
//    var array = new Uint8Array(new ArrayBuffer(rawLength));
//
//    for (i = 0; i < rawLength; i++) {
//        array[i] = raw.charCodeAt(i);
//    }
//    return array;
//}