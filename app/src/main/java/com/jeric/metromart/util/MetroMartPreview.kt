package com.jeric.metromart.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Phone - Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFF0F0F0, device = "spec:width=360dp,height=640dp,dpi=420")
@Preview(name = "Phone - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, backgroundColor = 0xFF121212, device = "spec:width=360dp,height=640dp,dpi=420")
@Preview(name = "Tablet - Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFF0F0F0, device = "spec:width=800dp,height=1280dp,dpi=320")
@Preview(name = "Tablet - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, backgroundColor = 0xFF121212, device = "spec:width=800dp,height=1280dp,dpi=320")
@Preview(name = "Foldable - Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFF0F0F0, device = "spec:width=673dp,height=841dp,dpi=480")
@Preview(name = "Foldable - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, backgroundColor = 0xFF121212, device = "spec:width=673dp,height=841dp,dpi=480")
annotation class MetroMartPreview