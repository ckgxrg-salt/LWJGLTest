#version 400 core
in vec4 color;
in vec2 texCoord;

out vec4 FragColor;

uniform sampler2D tex;

void main(){
    FragColor = texture(tex, texCoord);
    if(FragColor.w < 1.0){
        discard;
    }
}