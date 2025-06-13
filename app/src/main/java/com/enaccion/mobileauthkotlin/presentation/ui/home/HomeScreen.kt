package com.enaccion.mobileauthkotlin.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enaccion.mobileauthkotlin.R
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CardOfertas
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CardTarjetaCreditoScreen
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.MiniCardScreen
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.NavbarScreen

//@Composable
//fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = viewModel()){
//
//}


@Preview
@Composable
fun HomeScreen(){

    /**
     * Navbar
     */
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            //.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        NavbarScreen()

        /**
         * Tarjetas de banco
         */
        CardTarjetaCreditoScreen()

        /**
         * Últimos Movimientos
         */
        Row (
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Column {
                Text(
                    text = "Últimos movimientos",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }

            Column {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        /**
         * Final Últimos Movimientos
         */

        /**
         * Destacados
         */
        Card (
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFeff4f5)),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(25.dp)
            ) {
                Text(
                    text = "Accesos destacados",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    // Minis cards
                    MiniCardScreen("Ir a\nSeguros", painterResource(id = R.drawable.paragua))

                    MiniCardScreen("Invierte \nSmart", painterResource(id = R.drawable.charts))

                    MiniCardScreen("Pide tu \nCrédito", painterResource(id = R.drawable.money))

                    MiniCardScreen("Misiones", painterResource(id = R.drawable.trophy))

                }
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Tus ofertas",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(15.dp))

                /**
                 * Cards de tus ofertas
                 */
                CardOfertas()

                Spacer(modifier = Modifier.height(15.dp))
                CardOfertas()
            }
        }
    }
}

