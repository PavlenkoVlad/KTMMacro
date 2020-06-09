function getDifficultyLevels() {
    return AJS.$.get("/../confluence/rest/ktm-rest/latest/difficultyLevel");
}

function getLabels() {
    return AJS.$.get("/../confluence/rest/ktm-rest/latest/label");
}

function getScopes() {
    return AJS.$.get("/../confluence/rest/ktm-rest/latest/scope");
}

function labelSelectorOnChange() {
    let valueArray = AJS.$("#macro-param-div-labelSelector").val();
    var labels = '';
    for (const value of valueArray) {
        labels += value + '; ';
    }

    document.getElementById("macro-param-Labels").value = labels;
}

AJS.$.when(getScopes(), getDifficultyLevels(), getLabels()).done(function(responseScopes, responseDl, responseLabels) {
    let scopes = responseScopes[0];
    let difficultyLevels = responseDl[0];
    let lables = responseLabels[0];

    AJS.MacroBrowser.setMacroJsOverride("Knowledge text mapper macro", {

        "fields": {

            "string": {
                "labelSelector": function (param) {
                    document.getElementById("macro-param-Labels").disabled = true;

                    var selectorHtml = '<select multiple style="width: 100%; display: block; font-size: 11pt;" onchange="labelSelectorOnChange()">';

                    for (const label of lables) {
                        selectorHtml += '<option style="font-size: 11pt">' + label.name + '</option>';
                    }

                    selectorHtml += '</select>';

                    var selectorDiv = AJS.$(selectorHtml);
                    var selector = AJS.$("#macro-param-div-labelSelector", selectorDiv);

                    return AJS.MacroBrowser.Field(selectorDiv, selector);
                }
            },

            "enum": {
                "Scope": function (param, options) {
                    var field = AJS.MacroBrowser.ParameterFields["enum"](param, options);

                    for (const scope of scopes) {
                        field.input.append('<option>' + scope.name + '</option>>');
                    }

                    return field;
                },

                "Difficulty": function (param, options) {
                    var field = AJS.MacroBrowser.ParameterFields["enum"](param, options);

                    for (const level of difficultyLevels) {
                        field.input.append('<option>' + level.name + '</option>');
                    }

                    return field;
                }
            }
        }
    });
});