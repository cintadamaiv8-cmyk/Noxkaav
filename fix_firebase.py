import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

content = content.replace('import com.google.gms.googleservices.GoogleServicesPlugin.MissingGoogleServicesStrategy\n', '')
content = content.replace('  alias(libs.plugins.google.services)\n', '')
content = content.replace('googleServices { missingGoogleServicesStrategy = MissingGoogleServicesStrategy.WARN }\n', '')
content = content.replace('  implementation(platform(libs.firebase.bom))\n', '')
content = content.replace('  implementation(libs.firebase.ai)\n', '')
content = content.replace('  implementation(libs.firebase.appcheck.recaptcha)\n', '')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
