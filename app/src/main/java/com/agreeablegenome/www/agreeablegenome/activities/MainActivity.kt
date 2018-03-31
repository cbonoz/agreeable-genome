package com.agreeablegenome.www.agreeablegenome.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.agreeablegenome.www.agreeablegenome.GenomeApplication
import com.agreeablegenome.www.agreeablegenome.R
import com.agreeablegenome.www.agreeablegenome.util.GenomeService
import com.agreeablegenome.www.agreeablegenome.util.PrefManager
import de.mateware.snacky.Snacky
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject
import com.afollestad.materialdialogs.MaterialDialog
import com.agreeablegenome.www.agreeablegenome.fragments.FavoritesFragment
import com.agreeablegenome.www.agreeablegenome.fragments.GenomeFragment
import com.agreeablegenome.www.agreeablegenome.fragments.RecipeFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var genomeService: GenomeService
    @Inject
    lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GenomeApplication.injectionComponent.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        replaceFragment(RecipeFragment(), getString(R.string.todays_recipe))

        if (isFirstLogin()) {
            showInfoDialog()
        }

        showWelcomeSnackbar()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun showInfoDialog() {
        prefManager.saveBoolean("firstLogin", false)
        MaterialDialog.Builder(this)
                .title("Welcome to ${getString(R.string.app_name)}")
                .content(R.string.description)
                .positiveText("Done")
                .icon(resources.getDrawable(R.drawable.zzz_exclamation))
                .show()
    }

    private fun isFirstLogin(): Boolean {
        return prefManager.getBoolean("firstLogin", true)
    }

    private fun showWelcomeSnackbar() {
        Snacky.builder()
                .setActivity(this)
                .setText(R.string.welcome_back)
                .setDuration(Snacky.LENGTH_SHORT)
                .success()
                .show();
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                genomeService.logout()
                Toast.makeText(applicationContext, getString(R.string.logged_out), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_recipes -> {
                replaceFragment(RecipeFragment(), getString(R.string.todays_recipe))
            }
            R.id.nav_favorites -> {
                replaceFragment(FavoritesFragment(), getString(R.string.favorites))
            }
            R.id.nav_genome -> {
                replaceFragment(GenomeFragment(), getString(R.string.your_genome))
            }
            R.id.nav_share -> {
                Toast.makeText(applicationContext, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_settings -> {
                Toast.makeText(applicationContext, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment, fragmentTitle: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        setTitle(fragmentTitle)
        transaction.commit()
    }
}
